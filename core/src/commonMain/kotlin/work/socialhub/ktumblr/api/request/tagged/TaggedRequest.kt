package work.socialhub.ktumblr.api.request.tagged

import work.socialhub.ktumblr.api.request.MapRequest
import kotlin.js.JsExport

@JsExport
class TaggedRequest : MapRequest {
    var tag: String? = null
    var before: Int? = null
    var limit: Int? = null
    var filter: String? = null

    @JsExport.Ignore
    override fun toMap() =
        mutableMapOf<String, Any>().also {
            it.addParam("tag", tag)
            it.addParam("before", before)
            it.addParam("limit", limit)
            it.addParam("filter", filter)
        }
}