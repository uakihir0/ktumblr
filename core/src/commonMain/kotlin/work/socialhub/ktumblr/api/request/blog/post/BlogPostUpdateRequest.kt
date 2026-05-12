package work.socialhub.ktumblr.api.request.blog.post

import kotlin.js.JsExport

@JsExport
class BlogPostUpdateRequest {
    var blogName: String? = null
    var id: String? = null
    var type: String? = null
    var title: String? = null
    var body: String? = null
    var slug: String? = null
    var startDate: String? = null
    var tzAddress: String? = null
    var replyKey: String? = null
    var tags: Array<String>? = null

    // Photo post fields
    var data: Array<work.socialhub.ktumblr.api.request.FileRequest>? = null
    var caption: String? = null
    var link: String? = null

    // Quote post fields
    var quoteText: String? = null
    var quoteSource: String? = null

    // Link post fields
    var linkUrl: String? = null
    var linkTitle: String? = null
    var linkDescription: String? = null

    // Chat post fields
    var chatTitle: String? = null
    var chatLabel: String? = null
    var chatDialogue: String? = null

    // Audio post fields
    var externalUrl: String? = null

    // Video post fields
    var embed: String? = null

    // Answer post fields
    var answer: String? = null

    fun toMap(): Map<String, Any> = mutableMapOf<String, Any>().also {
        it.addParam("id", id)
        it.addParam("type", type)
        it.addParam("title", title)
        it.addParam("body", body)
        it.addParam("slug", slug)
        it.addParam("start_date", startDate)
        it.addParam("tz_address", tzAddress)
        it.addParam("reply_key", replyKey)
        it.addParam("tags", tags)
        it.addParam("caption", caption)
        it.addParam("link", link)
        it.addParam("quote_text", quoteText)
        it.addParam("quote_source", quoteSource)
        it.addParam("link_url", linkUrl)
        it.addParam("link_title", linkTitle)
        it.addParam("link_description", linkDescription)
        it.addParam("chat_title", chatTitle)
        it.addParam("chat_label", chatLabel)
        it.addParam("chat_dialogue", chatDialogue)
        it.addParam("external_url", externalUrl)
        it.addParam("embed", embed)
        it.addParam("answer", answer)
    }

    fun toFileMap(): Map<String, Pair<String, ByteArray>> {
        if (data.isNullOrEmpty()) return mapOf()
        return data!!.mapIndexed { index, file ->
            "data[$index]" to (file.name to file.data)
        }.toMap()
    }

    private fun MutableMap<String, Any>.addParam(
        key: String, value: Any?
    ) {
        if (value == null) return
        this[key] = value
    }
}
