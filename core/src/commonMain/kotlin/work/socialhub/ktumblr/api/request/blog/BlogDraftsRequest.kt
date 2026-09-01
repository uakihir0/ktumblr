package work.socialhub.ktumblr.api.request.blog

import work.socialhub.ktumblr.api.request.MapRequest
import kotlin.js.JsExport

@JsExport
class BlogDraftsRequest : MapRequest {
    var blogName: String? = null

    /** Post ID, as a string. See UserDashboardRequest.sinceId. */
    var beforeId: String? = null

    var filter: String? = null

    @JsExport.Ignore
    override fun toMap() =
        mutableMapOf<String, Any>().also {
            it.addParam("before_id", beforeId)
            it.addParam("filter", filter)
        }
}