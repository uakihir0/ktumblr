package work.socialhub.ktumblr.entity

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlin.js.JsExport

@JsExport
@Serializable
class Note {

    @SerialName("type")
    val type: String? = null

    @SerialName("timestamp")
    val timestamp: Int? = null

    @SerialName("blog_name")
    val blogName: String? = null

    @SerialName("blog_uuid")
    val blogUuid: String? = null

    @SerialName("blog_url")
    val blogUrl: String? = null

    @SerialName("followed")
    val isFollowed: Boolean? = null

    @SerialName("avatar_shape")
    val avatarShape: String? = null

    @SerialName("post_id")
    val postId: String? = null

    @SerialName("reblog_parent_blog_name")
    val reblogParentBlogName: String? = null

    // val replyText: String? = null
    // val addedText: String? = null
}
