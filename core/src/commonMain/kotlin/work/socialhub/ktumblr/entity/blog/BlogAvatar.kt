package work.socialhub.ktumblr.entity.blog

import kotlinx.serialization.Serializable
import kotlin.js.JsExport

@JsExport
@Serializable
class BlogAvatar {

    var width: Int? = null
    var height: Int? = null
    var url: String? = null
    // TODO: accessories
}