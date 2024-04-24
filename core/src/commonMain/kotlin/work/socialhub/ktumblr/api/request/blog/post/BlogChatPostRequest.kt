package work.socialhub.ktumblr.api.request.blog.post

import work.socialhub.ktumblr.api.request.MapRequest
import kotlin.js.JsExport

@JsExport
class BlogChatPostRequest :
    MapRequest,
    BlogPostRequest() {

    var title: String? = null
    var conversation: String? = null

    @JsExport.Ignore
    override fun toMap() =
        toBaseMap().also {
            it.addParam("title", title)
            it.addParam("conversation", conversation)
        }
}