package work.socialhub.ktumblr.entity.post.legacy

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import work.socialhub.ktumblr.entity.post.Post
import work.socialhub.ktumblr.entity.post.options.Dialogue
import kotlin.js.JsExport

/**
 * This class represents a post of type "chat"
 */
@JsExport
@Serializable
class LegacyChatPost : Post() {

    @SerialName("dialogue")
    val dialogue: Array<Dialogue>? = null
}
