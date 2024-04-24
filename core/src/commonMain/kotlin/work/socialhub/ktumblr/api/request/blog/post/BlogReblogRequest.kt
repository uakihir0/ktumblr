package work.socialhub.ktumblr.api.request.blog.post

import work.socialhub.ktumblr.api.request.MapRequest
import kotlin.js.JsExport

@JsExport
class BlogReblogRequest : MapRequest {
    var blogName: String? = null

    var id: String? = null
    var reblogKey: String? = null
    var comment: String? = null
    var nativeInlineImages: Boolean? = null

    @JsExport.Ignore
    override fun toMap() =
        mutableMapOf<String, Any>()
            .also {
                it.addParam("id", id)
                it.addParam("reblog_key", reblogKey)
                it.addParam("comment", comment)
                it.addParam("native_inline_images", nativeInlineImages)
            }
}