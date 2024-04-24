package work.socialhub.ktumblr.api.request.blog

import kotlin.js.JsExport

@JsExport
class BlogAvatarRequest {
    var blogName: String? = null
    var size: Int? = 0
}