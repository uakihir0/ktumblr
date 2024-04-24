package work.socialhub.ktumblr.entity.post.legacy

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import work.socialhub.ktumblr.entity.post.Post
import kotlin.js.JsExport

@JsExport
@Serializable
class LegacyAudioPost : Post() {

    @SerialName("caption")
    var caption: String? = null

    // TODO
}