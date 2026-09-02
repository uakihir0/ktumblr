package work.socialhub.ktumblr

import work.socialhub.ktumblr.api.request.blog.BlogDraftsRequest
import work.socialhub.ktumblr.api.request.blog.BlogPostsRequest
import work.socialhub.ktumblr.api.request.user.UserDashboardRequest
import kotlin.test.Test
import kotlin.test.assertEquals

/**
 * Query parameters built from the request objects.
 */
class RequestParamTest {

    /** A real post ID from /v2/user/dashboard: well past Int, and past 2^53. */
    private val postId = "826528809650126848"

    /**
     * Post IDs are 64-bit, so they have to travel as strings. When these
     * parameters were typed `Int` the caller had to truncate the ID first, which
     * either overflowed or silently paged from the wrong post.
     */
    @Test
    fun keepsFullPostIdsInParams() {
        assertEquals(
            postId,
            UserDashboardRequest()
                .also { it.sinceId = postId }
                .toMap()["since_id"]
        )
        assertEquals(
            postId,
            BlogPostsRequest()
                .also { it.id = postId }
                .toMap()["id"]
        )
        assertEquals(
            postId,
            BlogDraftsRequest()
                .also { it.beforeId = postId }
                .toMap()["before_id"]
        )
    }

    @Test
    fun omitsUnsetParams() {
        assertEquals(emptyMap(), UserDashboardRequest().toMap())
        assertEquals(emptyMap(), BlogDraftsRequest().toMap())
    }
}
