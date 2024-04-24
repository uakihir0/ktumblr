package work.socialhub.ktumblr.entity.trail

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import work.socialhub.ktumblr.entity.trail.BlogInTrail
import work.socialhub.ktumblr.entity.trail.IdInTrail
import kotlin.js.JsExport

@JsExport
@Serializable
class Trail {

    val blog: BlogInTrail? = null
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
