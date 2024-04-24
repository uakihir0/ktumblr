package work.socialhub.ktumblr.entity.post.options

import kotlinx.serialization.Serializable
import kotlin.js.JsExport

/**
 * This class represents a photo at a given size
 */
@JsExport
@Serializable
class PhotoSize {
    val width: Int = 0
    val height: Int = 0
    val url: String? = null
}
