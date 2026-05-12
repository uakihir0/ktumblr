package work.socialhub.ktumblr.api.request.blog.post

import work.socialhub.ktumblr.api.request.FileRequest
import work.socialhub.ktumblr.api.request.MapRequest
import kotlin.js.JsExport

@JsExport
class BlogPostUpdateRequest : MapRequest {
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

    var data: Array<FileRequest>? = null
    var caption: String? = null
    var link: String? = null

    var quoteText: String? = null
    var quoteSource: String? = null

    var linkUrl: String? = null
    var linkTitle: String? = null
    var linkDescription: String? = null

    var chatTitle: String? = null
    var chatLabel: String? = null
    var chatDialogue: String? = null

    var externalUrl: String? = null

    var embed: String? = null

    var answer: String? = null

    @JsExport.Ignore
    override fun toMap(): Map<String, Any> = mutableMapOf<String, Any>().also {
        it.addParam("id", id)
        it.addParam("type", type)
        it.addParam("title", title)
        it.addParam("body", body)
        it.addParam("slug", slug)
        it.addParam("start_date", startDate)
        it.addParam("tz_address", tzAddress)
        it.addParam("reply_key", replyKey)
        it.addParam("tags", tags?.joinToString(","))
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
}
