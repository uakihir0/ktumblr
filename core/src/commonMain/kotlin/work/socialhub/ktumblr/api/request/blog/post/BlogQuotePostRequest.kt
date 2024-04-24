package work.socialhub.ktumblr.api.request.blog.post

import work.socialhub.ktumblr.api.request.MapRequest
import kotlin.js.JsExport

@JsExport
class BlogQuotePostRequest :
    MapRequest,
    BlogPostRequest() {

    var quote: String? = null
    var source: String? = null

    @JsExport.Ignore
    override fun toMap() =
        toBaseMap().also {
            it.addParam("quote", quote)
            it.addParam("source", source)
        }
}