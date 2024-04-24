package work.socialhub.ktumblr.entity.post.options

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlin.js.JsExport

/**
 * This class represents a Photo in a PhotoPost
 */
@JsExport
@Serializable
class Photo {

    @SerialName("caption")
    val caption: String? = null

    @SerialName("original_size")
    val originalSize: PhotoSize? = null

    @SerialName("alt_sizes")
    val altSizes: Array<PhotoSize>? = null
}
