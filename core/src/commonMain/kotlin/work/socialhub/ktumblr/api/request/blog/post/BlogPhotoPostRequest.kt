package work.socialhub.ktumblr.api.request.blog.post

import work.socialhub.ktumblr.api.request.MapRequest
import kotlin.js.JsExport

@JsExport
class BlogPhotoPostRequest :
    MapRequest,
    BlogPostRequest() {

    var caption: String? = null
    var link: String? = null
    var source: String? = null
    var data: String? = null
    var data64: String? = null

    @JsExport.Ignore
    override fun toMap() =
        toBaseMap().also {
            it.addParam("caption", caption)
            it.addParam("link", link)
            it.addParam("source", source)
            it.addParam("data", data)
            it.addParam("data64", data64)
        }
}