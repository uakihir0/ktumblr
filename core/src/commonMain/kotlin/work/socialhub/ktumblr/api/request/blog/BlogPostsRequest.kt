package work.socialhub.ktumblr.api.request.blog

import work.socialhub.ktumblr.api.request.MapRequest
import kotlin.js.JsExport

@JsExport
class BlogPostsRequest: MapRequest {
    var blogName: String? = null
    var type: String? = null

    var id: Int? = null
    var tag: String? = null
    var limit: Int? = null
    var offset: Int? = null
    var reblogInfo: Boolean? = null
    var notesInfo: Boolean? = null
    var filter: String? = null
    var before: Int? = null
    var npf: Boolean? = null

    @JsExport.Ignore
    override fun toMap() =
        mutableMapOf<String, Any>().also {
            it.addParam("id", id)
            it.addParam("tag", tag)
            it.addParam("limit", limit)
            it.addParam("offset", offset)
            it.addParam("reblog_info", reblogInfo)
            it.addParam("notes_info", notesInfo)
            it.addParam("filter", filter)
            it.addParam("before", before)
            it.addParam("npf", npf)
        }
}