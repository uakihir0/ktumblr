package work.socialhub.ktumblr.entity.post.legacy

import kotlinx.serialization.Serializable
import work.socialhub.ktumblr.entity.post.Post
import kotlin.js.JsExport

@JsExport
@Serializable
class LegacyQuotePost : Post() {

    var text: String? = null
    var source: String? = null
}
