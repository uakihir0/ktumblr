package work.socialhub.ktumblr.api.request.blog.post

import work.socialhub.ktumblr.api.request.MapRequest
import kotlin.js.JsExport

@JsExport
class BlogTextPostRequest :
    MapRequest,
    BlogPostRequest() {

    var title: String? = null
    var body: String? = null

    @JsExport.Ignore
    override fun toMap() =
        toBaseMap().also {
            it.addParam("title", title)
            it.addParam("body", body)
        }
}