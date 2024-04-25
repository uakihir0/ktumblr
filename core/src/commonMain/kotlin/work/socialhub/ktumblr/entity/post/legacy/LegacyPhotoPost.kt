package work.socialhub.ktumblr.entity.post.legacy

import kotlinx.serialization.Serializable
import work.socialhub.ktumblr.entity.post.Post
import work.socialhub.ktumblr.entity.post.options.Photo
import kotlin.js.JsExport

/**
 * This class represents a post of type "photo"
 */
@JsExport
@Serializable
class LegacyPhotoPost : Post() {

    var caption: String? = null
    var width: Int? = null
    var height: Int? = null
    var photos: Array<Photo>? = null
}
