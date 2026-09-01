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
}
