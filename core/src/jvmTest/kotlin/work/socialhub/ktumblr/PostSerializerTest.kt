package work.socialhub.ktumblr

import work.socialhub.ktumblr.api.response.Body
import work.socialhub.ktumblr.api.response.user.UserDashboardResponse
import work.socialhub.ktumblr.entity.post.legacy.LegacyPhotoPost
import work.socialhub.ktumblr.entity.post.legacy.LegacyTextPost
import work.socialhub.ktumblr.util.Json.fromJson
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotNull
import kotlin.test.assertTrue

/**
 * Deserialization of /v2/user/dashboard payloads.
 */
class PostSerializerTest {

    private fun dashboard(posts: String): UserDashboardResponse {
        val body = fromJson<Body<UserDashboardResponse>>(
            """{"meta":{"status":200,"msg":"OK"},"response":{"posts":[$posts]}}"""
        )
        return assertNotNull(body.response)
    }

    @Test
    fun decodesKnownPostTypes() {
        val response = dashboard(
            """
            {"type":"text","id_string":"1","blog_name":"a","body":"<p>hi</p>"},
            {"type":"photo","id_string":"2","blog_name":"b","caption":"<p>hi</p>"}
            """.trimIndent()
        )

        val posts = assertNotNull(response.posts)
        assertEquals(2, posts.size)
        assertTrue(posts[0] is LegacyTextPost)
        assertTrue(posts[1] is LegacyPhotoPost)
    }

    /**
     * Tumblr keeps adding post types. An unmodelled `type` used to resolve to
     * `Post.serializer()`, which is `PostSerializer` itself — the lookup recursed
     * until the stack overflowed and the entire page failed to decode. Unknown
     * types must degrade to the shared fields instead.
     */
    @Test
    fun decodesUnknownPostTypeWithoutRecursing() {
        val response = dashboard(
            """{"type":"blocks","id_string":"3","blog_name":"c","timestamp":1788230293,"content":[{"type":"text","text":"hi"}]}"""
        )

        val posts = assertNotNull(response.posts)
        assertEquals(1, posts.size)
        assertEquals("3", posts[0].idString)
        assertEquals("c", posts[0].blogName)
        assertEquals("blocks", posts[0].type)
        assertEquals(1788230293, posts[0].timestamp)
    }

    @Test
    fun decodesPostWithoutTypeWithoutRecursing() {
        val response = dashboard("""{"id_string":"4","blog_name":"d"}""")

        val posts = assertNotNull(response.posts)
        assertEquals(1, posts.size)
        assertEquals("4", posts[0].idString)
    }

    /**
     * The dashboard mixes blogs whose themes disagree on the type of
     * `header_bounds` (int vs. string) and carries keys this library does not
     * model — neither may break decoding.
     */
    @Test
    fun decodesTrailThemesWithVaryingFieldTypes() {
        val response = dashboard(
            """
            {"type":"text","id_string":"5","blog_name":"e","body":"<p>hi</p>","trail":[
              {"blog":{"name":"e","active":true,"theme":{"header_bounds":0,"header_image":"https://x/a.jpg","header_stretch":true}},
               "post":{"id":"5"},"content_raw":"<p>hi</p>","content":"<p>hi</p>","is_current_item":true,"is_root_item":true}]},
            {"type":"text","id_string":"6","blog_name":"f","body":"<p>hi</p>","trail":[
              {"blog":{"name":"f","active":true,"theme":{"header_bounds":"","header_full_width":1594,"header_image":"https://x/b.jpg","header_stretch":true}},
               "post":{"id":"6"},"content_raw":"<p>hi</p>","content":"<p>hi</p>","is_current_item":true,"is_root_item":true}]}
            """.trimIndent()
        )

        val posts = assertNotNull(response.posts)
        assertEquals(2, posts.size)
        assertEquals("https://x/a.jpg", posts[0].trail?.get(0)?.blog?.theme?.headerImage)
        assertEquals("https://x/b.jpg", posts[1].trail?.get(0)?.blog?.theme?.headerImage)

        // `header_bounds` is documented as "Mixed": both of these are the
        // "no crop" value, they just disagree on the JSON type.
        assertEquals("0", posts[0].trail?.get(0)?.blog?.theme?.headerBounds)
        assertEquals("", posts[1].trail?.get(0)?.blog?.theme?.headerBounds)
    }

    /**
     * The documented shape of `header_bounds` — the crop coordinates — plus the
     * array form, which normalises to the same comma-separated string.
     */
    @Test
    fun decodesHeaderBoundsCropCoordinates() {
        val response = dashboard(
            """
            {"type":"text","id_string":"10","blog_name":"j","body":"<p>hi</p>",
             "blog":{"name":"j","url":"https://j.tumblr.com/","theme":{"header_bounds":"0,1280,320,0"}}},
            {"type":"text","id_string":"11","blog_name":"k","body":"<p>hi</p>",
             "blog":{"name":"k","url":"https://k.tumblr.com/","theme":{"header_bounds":[0,1280,320,0]}}}
            """.trimIndent()
        )

        val posts = assertNotNull(response.posts)
        assertEquals("0,1280,320,0", posts[0].blog?.theme?.headerBounds)
        assertEquals("0,1280,320,0", posts[1].blog?.theme?.headerBounds)
    }

    /**
     * A deactivated blog in a trail is served with `"theme": []` instead of an
     * object. kotlinx.serialization rejects that outright, and since the page is
     * decoded in one pass it took down every post in the response, not just the
     * one carrying that trail entry — this is what blanked the dashboard.
     *
     * `[]` for an empty object is not documented in the v2 API reference; it is
     * observed behaviour, so decoding stays lenient rather than strict.
     */
    @Test
    fun decodesThemeSentAsEmptyArray() {
        val response = dashboard(
            """
            {"type":"text","id_string":"8","blog_name":"h","body":"<p>hi</p>","trail":[
              {"blog":{"name":"h","active":true,"theme":{"header_image":"https://x/a.jpg"}},
               "post":{"id":"8"},"content_raw":"<p>hi</p>","is_root_item":true,"is_current_item":true}]},
            {"type":"photo","id_string":"9","blog_name":"i","caption":"<p>hi</p>","trail":[
              {"blog":{"name":"gone","active":false,"theme":[],"share_likes":false,
                       "share_following":false,"can_be_followed":false},
               "post":{"id":"9"},"content_raw":"<p>hi</p>","is_root_item":true}]}
            """.trimIndent()
        )

        // The whole page still decodes ...
        val posts = assertNotNull(response.posts)
        assertEquals(2, posts.size)
        assertEquals("https://x/a.jpg", posts[0].trail?.get(0)?.blog?.theme?.headerImage)

        // ... and the themeless blog simply has no theme values.
        val gone = assertNotNull(assertNotNull(posts[1].trail)[0].blog)
        assertEquals("gone", gone.name)
        assertEquals(null, gone.theme?.headerImage)
    }

    /**
     * The trail flags are sent as `is_current_item` / `is_root_item`; without that
     * prefix they always decoded as false, so callers could not tell which entry
     * the post itself contributed.
     *
     * Tumblr only emits them when true, so absence must keep meaning false.
     */
    @Test
    fun decodesTrailItemFlags() {
        val response = dashboard(
            """
            {"type":"text","id_string":"7","blog_name":"g","body":"<p>hi</p>","trail":[
              {"blog":{"name":"root"},"post":{"id":"1"},"content_raw":"<p>root</p>","is_current_item":false,"is_root_item":true},
              {"blog":{"name":"g"},"post":{"id":"7"},"content_raw":"<p>hi</p>","is_current_item":true,"is_root_item":false}]}
            """.trimIndent()
        )

        val trail = assertNotNull(assertNotNull(response.posts)[0].trail)
        assertEquals(2, trail.size)
        assertTrue(trail[0].isRootItem)
        assertTrue(!trail[0].isCurrentItem)
        assertTrue(trail[1].isCurrentItem)
        assertTrue(!trail[1].isRootItem)
    }
}
