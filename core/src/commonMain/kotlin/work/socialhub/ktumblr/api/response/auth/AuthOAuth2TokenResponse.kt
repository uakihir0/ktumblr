package work.socialhub.ktumblr.api.response.auth

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlin.js.JsExport

@JsExport
@Serializable
class AuthOAuth2TokenResponse {

    @SerialName("access_token")
    var accessToken: String? = null

    @SerialName("expires_in")
    var expiresIn: Int? = null

    @SerialName("token_type")
    var tokenType: String? = null

    @SerialName("scope")
    var scope: String? = null

    @SerialName("refresh_token")
    var refreshToken: String? = null
}