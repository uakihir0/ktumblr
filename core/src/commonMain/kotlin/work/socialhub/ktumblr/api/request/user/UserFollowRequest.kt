package work.socialhub.ktumblr.api.request.user

import work.socialhub.ktumblr.api.request.MapRequest
import kotlin.js.JsExport

@JsExport
class UserFollowRequest : MapRequest {
    var url: String? = null
    var email: String? = null

    @JsExport.Ignore
    override fun toMap() =
        mutableMapOf<String, Any>().also {
            it.addParam("url", url)
            it.addParam("email", email)
        }
}