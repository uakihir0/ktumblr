package work.socialhub.ktumblr.entity.post.legacy

import kotlinx.serialization.Serializable
import work.socialhub.ktumblr.entity.post.options.Video
import work.socialhub.ktumblr.entity.post.Post
import kotlin.js.JsExport

/**
 * This class represents a post of type "video"
 */
@JsExport
@Serializable
class LegacyVideoPost : Post() {
    var caption: String? = null
    val player: Array<Video>? = null
}
