package work.socialhub.ktumblr.entity

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import work.socialhub.ktumblr.entity.Resource
import work.socialhub.ktumblr.entity.blog.Blog
import kotlin.js.JsExport

/**
 * This class represents a User on Tumblr
 */
@JsExport
@Serializable
class User : Resource() {

    @SerialName("following")
    val followingCount: Int? = null

    @SerialName("default_post_format")
    val defaultPostFormat: String? = null

    @SerialName("name")
    val name: String? = null

    @SerialName("likes")
    val likeCount: Int? = null

    @SerialName("blogs")
    val blogs: Array<Blog>? = null
}
