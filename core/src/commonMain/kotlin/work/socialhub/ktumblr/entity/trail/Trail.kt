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

    // The API sends these as `is_current_item` / `is_root_item`. They used to be
    // declared without the prefix, so both always decoded as false and callers
    // could never tell which trail entry the post itself contributed.
    @SerialName("is_current_item")
    val isCurrentItem: Boolean = false

    @SerialName("is_root_item")
    val isRootItem: Boolean = false
}
