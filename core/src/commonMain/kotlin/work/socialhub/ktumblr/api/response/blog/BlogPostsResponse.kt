package work.socialhub.ktumblr.api.response.blog

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import work.socialhub.ktumblr.entity.blog.Blog
import work.socialhub.ktumblr.entity.post.Post
import kotlin.js.JsExport

@JsExport
@Serializable
class BlogPostsResponse {

    @SerialName("blog")
    var blog: Blog? = null
    @SerialName("posts")
    var posts: Array<Post>? = null
    @SerialName("total_posts")
    var totalPosts: Int? = null
}