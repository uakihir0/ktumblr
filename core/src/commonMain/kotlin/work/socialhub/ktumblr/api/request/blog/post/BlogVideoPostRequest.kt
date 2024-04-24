package work.socialhub.ktumblr.api.request.blog.post

import work.socialhub.ktumblr.api.request.MapRequest
import kotlin.js.JsExport

@JsExport
class BlogVideoPostRequest :
    MapRequest,
    BlogPostRequest() {

    var caption: String? = null
    var embed: String? = null
    var data: String? = null

    @JsExport.Ignore
    override fun toMap() =
        toBaseMap().also {
            it.addParam("caption", caption)
            it.addParam("embed", embed)
            it.addParam("data", data)
        }
}