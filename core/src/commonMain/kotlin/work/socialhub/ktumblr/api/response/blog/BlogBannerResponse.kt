package work.socialhub.ktumblr.api.response.blog

import kotlinx.serialization.Serializable
import kotlin.js.JsExport

@JsExport
@Serializable
class BlogBannerResponse {
    var banner: String? = null
    var height: Int? = null
    var width: Int? = null
}
