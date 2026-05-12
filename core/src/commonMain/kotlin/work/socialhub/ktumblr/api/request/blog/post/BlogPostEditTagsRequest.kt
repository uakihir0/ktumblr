package work.socialhub.ktumblr.api.request.blog.post

import kotlin.js.JsExport

@JsExport
class BlogPostEditTagsRequest {
    var blogName: String? = null
    var id: String? = null
    var tags: Array<String>? = null

    fun toMap(): Map<String, Any> = mutableMapOf<String, Any>().also {
        it.addParam("id", id)
        it.addParam("tags", tags)
    }

    private fun MutableMap<String, Any>.addParam(
        key: String, value: Any?
    ) {
        if (value == null) return
        this[key] = value
    }
}
