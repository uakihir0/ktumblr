package work.socialhub.ktumblr.api.request.blog.post

import work.socialhub.ktumblr.api.request.MapRequest
import kotlin.js.JsExport

@JsExport
class BlogAudioPostRequest:
    MapRequest,
    BlogPostRequest() {

    var caption: String? = null
    var externalUrl: String? = null
    var data: String? = null

    @JsExport.Ignore
    override fun toMap() =
        toBaseMap().also {
            it.addParam("caption", caption)
            it.addParam("external_url", externalUrl)
            it.addParam("data", data)
        }
}