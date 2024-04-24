package work.socialhub.ktumblr.entity.post.options

import kotlinx.serialization.Serializable
import kotlin.js.JsExport

/**
 * An individual Video in a VideoPost
 */
@JsExport
@Serializable
class Video {
    val width: Int? = null
    val embedCode: String? = null
}
