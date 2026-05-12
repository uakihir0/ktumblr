package work.socialhub.ktumblr.api.request.blog

import kotlin.js.JsExport

@JsExport
class BlogBannerRequest {
    var blogName: String? = null
    var size: String? = null
}
