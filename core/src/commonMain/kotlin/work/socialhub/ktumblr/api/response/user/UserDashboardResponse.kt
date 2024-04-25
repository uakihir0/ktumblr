package work.socialhub.ktumblr.api.response.user

import kotlinx.serialization.Serializable
import work.socialhub.ktumblr.entity.post.Post
import kotlin.js.JsExport

@JsExport
@Serializable
class UserDashboardResponse {
    var posts: Array<Post>? = null
}