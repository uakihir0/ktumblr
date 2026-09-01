package work.socialhub.ktumblr.api.request.user

import work.socialhub.ktumblr.api.request.MapRequest
import kotlin.js.JsExport

@JsExport
class UserDashboardRequest : MapRequest {

    var limit: Int? = null
    var offset: Int? = null
    var type: String? = null

    /**
     * Post ID to page from, as a string.
     *
     * The reference documents `since_id` as a number, but post IDs are 64-bit
     * integers: `Int` truncates every real ID and `Long` has no faithful
     * representation on Kotlin/JS. Pass the post's `id_string` through
     * unchanged — the API reads the decimal digits either way.
     */
    var sinceId: String? = null
    var reblogInfo: Boolean? = null
    var notesInfo: Boolean? = null
    var npf: Boolean? = null

    @JsExport.Ignore
    override fun toMap() =
        mutableMapOf<String, Any>().also {
            it.addParam("limit", limit)
            it.addParam("offset", offset)
            it.addParam("type", type)
            it.addParam("since_id", sinceId)
            it.addParam("reblog_info", reblogInfo)
            it.addParam("notes_info", notesInfo)
            it.addParam("npf", npf)
        }
}