package work.socialhub.ktumblr.api.request.blog.post

import work.socialhub.ktumblr.api.request.MapRequest
import kotlin.js.JsExport

@JsExport
class BlogDeleteRequest : MapRequest {
    var blogName: String? = null

    var id: String? = null

    @JsExport.Ignore
    override fun toMap() =
        mutableMapOf<String, Any>()
            .also {
                it.addParam("id", id)
            }
}