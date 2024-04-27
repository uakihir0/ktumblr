package work.socialhub.ktumblr.entity.trail

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlin.js.JsExport

@JsExport
@Serializable
class Trail {

    @SerialName("blog")
    val blog: BlogInTrail? = null

    @SerialName("post")
    val post: IdInTrail? = null

    @SerialName("content_raw")
    val contentRaw: String? = null

    @SerialName("content")
    val content: String? = null

    @SerialName("current_item")
    val isCurrentItem: Boolean = false

    @SerialName("root_item")
    val isRootItem: Boolean = false
}
