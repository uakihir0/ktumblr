package work.socialhub.ktumblr.api.request.auth

import work.socialhub.ktumblr.api.request.MapRequest
import kotlin.js.JsExport

@JsExport
class AuthOAuth2TokenRefreshRequest : MapRequest {

    var grantType: String = "refresh_token"
    var clientId: String? = null
    var clientSecret: String? = null
    var refreshToken: String? = null

    @JsExport.Ignore
    override fun toMap(): Map<String, Any> {
        return mutableMapOf<String, Any>().also {
            it.addParam("grant_type", grantType)
            it.addParam("client_id", clientId)
            it.addParam("client_secret", clientSecret)
            it.addParam("refresh_token", refreshToken)
        }
    }
}