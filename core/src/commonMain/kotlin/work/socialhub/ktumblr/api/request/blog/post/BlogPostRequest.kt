package work.socialhub.ktumblr.api.request.blog.post

import work.socialhub.ktumblr.api.request.addParam
import kotlin.js.JsExport

@JsExport
open class BlogPostRequest {
    var blogName: String? = null

    /** OnlyForEdit */
    var id: String? = null

    var type: String? = null
    var state: String? = null
    var tags: String? = null
    var tweet: String? = null
    var date: String? = null
    var format: String? = null
    var slug: String? = null
    var nativeInlineImages: Boolean? = null
    
    @JsExport.Ignore
    fun toBaseMap() = mutableMapOf<String, Any>()
        .also {
            it.addParam("id", id)
            it.addParam("type", type)
            it.addParam("state", state)
            it.addParam("tags", tags)
            it.addParam("tweet", tweet)
            it.addParam("date", date)
            it.addParam("format", format)
            it.addParam("slug", slug)
            it.addParam("native_inline_images", nativeInlineImages)
        }
}