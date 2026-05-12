package work.socialhub.ktumblr.api.request.blog

import kotlin.js.JsExport

@JsExport
class BlogUpdateInfoRequest {
    var blogName: String? = null
    var name: String? = null
    var title: String? = null
    var description: String? = null
    var ask: String? = null
    var submit: String? = null
    var isDescriptionIndexable: Boolean? = null
    var isIndexFollowed: Boolean? = null

    fun toMap(): Map<String, Any> = mutableMapOf<String, Any>().also {
        it.addParam("name", name)
        it.addParam("title", title)
        it.addParam("description", description)
        it.addParam("ask", ask)
        it.addParam("submit", submit)
        it.addParam("is_description_indexable", isDescriptionIndexable)
        it.addParam("is_index_followed", isIndexFollowed)
    }

    private fun MutableMap<String, Any>.addParam(
        key: String, value: Any?
    ) {
        if (value == null) return
        this[key] = value
    }
}
