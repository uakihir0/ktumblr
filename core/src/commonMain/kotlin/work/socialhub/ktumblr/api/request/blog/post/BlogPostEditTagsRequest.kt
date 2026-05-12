package work.socialhub.ktumblr.api.request.blog.post

import work.socialhub.ktumblr.api.request.MapRequest
import kotlin.js.JsExport

@JsExport
class BlogPostEditTagsRequest : MapRequest {
    var blogName: String? = null
    var id: String? = null
    var tags: Array<String>? = null

    @JsExport.Ignore
    override fun toMap(): Map<String, Any> = mutableMapOf<String, Any>().also {
        it.addParam("id", id)
        it.addParam("tags", tags?.joinToString(","))
    }
}
