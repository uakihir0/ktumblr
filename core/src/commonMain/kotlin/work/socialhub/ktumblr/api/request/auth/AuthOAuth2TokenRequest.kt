package work.socialhub.ktumblr.api.request.auth

import work.socialhub.ktumblr.api.request.MapRequest
import kotlin.js.JsExport

@JsExport
class AuthOAuth2TokenRequest: MapRequest {

    var grantType: String = "authorization_code"
    var code: String? = null
    var clientId: String? = null
    var clientSecret: String? = null
    var redirectUri: String? = null

    @JsExport.Ignore
    override fun toMap(): Map<String, Any> {
        return mutableMapOf<String, Any>().also {
            it.addParam("grant_type", grantType)
            it.addParam("code", code)
            it.addParam("client_id", clientId)
            it.addParam("client_secret", clientSecret)
            it.addParam("redirect_uri", redirectUri)
        }
    }
}