package work.socialhub.ktumblr.api.response.user

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import work.socialhub.ktumblr.entity.blog.Blog
import kotlin.js.JsExport

@JsExport
@Serializable
class UserFollowingResponse {

    @SerialName("total_blogs")
    var totalBlog: Int? = null

    @SerialName("blogs")
    var blogs: Array<Blog>? = null
}