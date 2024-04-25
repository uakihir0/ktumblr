package work.socialhub.ktumblr.api.request.auth

import work.socialhub.ktumblr.api.request.MapRequest
import kotlin.js.JsExport

@JsExport
class AuthAuthorizeUrlRequest : MapRequest {

    var responseType: String = "code"
    var redirectUri: String? = null
    var state: String? = null
    var scope: String = "write offline_access"

    @JsExport.Ignore
    override fun toMap() =
        mutableMapOf<String, Any>().also {
            it.addParam("response_type", responseType)
            it.addParam("redirect_uri", redirectUri)
            it.addParam("state", state)
            it.addParam("scope", scope)
        }
}