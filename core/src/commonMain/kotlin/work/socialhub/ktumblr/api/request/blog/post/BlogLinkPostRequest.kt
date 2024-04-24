package work.socialhub.ktumblr.api.request.blog.post

import work.socialhub.ktumblr.api.request.MapRequest
import kotlin.js.JsExport

@JsExport
class BlogLinkPostRequest :
    MapRequest,
    BlogPostRequest() {

    var title: String? = null
    var url: String? = null
    var description: String? = null
    var thumbnail: String? = null
    var excerpt: String? = null
    var author: String? = null

    @JsExport.Ignore
    override fun toMap() =
        toBaseMap().also {
            it.addParam("title", title)
            it.addParam("url", url)
            it.addParam("description", description)
            it.addParam("thumbnail", thumbnail)
            it.addParam("excerpt", excerpt)
            it.addParam("author", author)
        }
}