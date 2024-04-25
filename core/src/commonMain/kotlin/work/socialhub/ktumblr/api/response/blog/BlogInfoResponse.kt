package work.socialhub.ktumblr.api.response.blog

import kotlinx.serialization.Serializable
import work.socialhub.ktumblr.entity.blog.Blog
import kotlin.js.JsExport

@JsExport
@Serializable
class BlogInfoResponse {
    var blog: Blog? = null
}