package work.socialhub.ktumblr.api.request.blog

import work.socialhub.ktumblr.api.request.MapRequest
import kotlin.js.JsExport

@JsExport
class BlogQueueRequest : MapRequest {
    var blogName: String? = null

    var limit: Int? = null
    var offset: Int? = null
    var filter: String? = null

    @JsExport.Ignore
    override fun toMap() =
        mutableMapOf<String, Any>().also {
            it.addParam("limit", limit)
            it.addParam("offset", offset)
            it.addParam("filter", filter)
        }
}