package work.socialhub.ktumblr.api.response.blog

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import work.socialhub.ktumblr.entity.blog.Blog
import kotlin.js.JsExport

@JsExport
@Serializable
class BlogFollowingResponse {

    @SerialName("blogs")
    var blogs: Array<Blog>? = null

    @SerialName("total_blogs")
    var totalBlogs: Int? = null
}