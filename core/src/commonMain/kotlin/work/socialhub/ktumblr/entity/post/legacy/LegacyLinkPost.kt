package work.socialhub.ktumblr.entity.post.legacy

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import work.socialhub.ktumblr.entity.post.Post
import kotlin.js.JsExport

/**
 * This class represents a Post of type "link"
 */
@JsExport
@Serializable
class LegacyLinkPost : Post() {

    @SerialName("description")
    var description: String? = null

    @SerialName("url")
    var url: String? = null

    @SerialName("link_author")
    var linkAuthor: String? = null

    @SerialName("excerpt")
    var excerpt: String? = null

    @SerialName("publisher")
    var publisher: String? = null

    // TODO: photos
    // @SerialName("photos")
    // var photos: Array<Photo>? = null
}
