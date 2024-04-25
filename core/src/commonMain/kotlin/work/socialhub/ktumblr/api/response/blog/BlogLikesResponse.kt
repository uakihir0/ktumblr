package work.socialhub.ktumblr.api.response.blog

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import work.socialhub.ktumblr.entity.post.Post
import kotlin.js.JsExport

@JsExport
@Serializable
class BlogLikesResponse {

    @SerialName("liked_posts")
    var likedPosts: Array<Post>? = null

    @SerialName("liked_count")
    var likedCount: Int? = null
}