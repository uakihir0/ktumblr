package work.socialhub.ktumblr.api.request.blog

import work.socialhub.ktumblr.api.request.MapRequest
import kotlin.js.JsExport

@JsExport
class BlogSubmissionsRequest: MapRequest {
    var blogName: String? = null

    var offset: String? = null
    var filter: String? = null

    @JsExport.Ignore
    override fun toMap() =
        mutableMapOf<String, Any>().also {
            it.addParam("offset", offset)
            it.addParam("filter", filter)
        }
}