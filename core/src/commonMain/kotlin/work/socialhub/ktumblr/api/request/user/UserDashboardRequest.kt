package work.socialhub.ktumblr.api.request.user

import work.socialhub.ktumblr.api.request.MapRequest
import kotlin.js.JsExport

@JsExport
class UserDashboardRequest : MapRequest {

    var limit: Int? = null
    var offset: Int? = null
    var type: String? = null
    var sinceId: Int? = null
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