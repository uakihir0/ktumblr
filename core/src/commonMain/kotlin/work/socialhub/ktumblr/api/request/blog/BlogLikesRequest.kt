package work.socialhub.ktumblr.api.request.blog

import work.socialhub.ktumblr.api.request.MapRequest
import kotlin.js.JsExport

@JsExport
class BlogLikesRequest : MapRequest {
    var blogName: String? = null
    var limit: Int? = null
    var offset: Int? = null
    var before: Int? = null
    var after: Int? = null

    @JsExport.Ignore
    override fun toMap() =
        mutableMapOf<String, Any>().also {
            it.addParam("limit", limit)
            it.addParam("offset", offset)
            it.addParam("before", before)
            it.addParam("after", after)
        }
}