package work.socialhub.ktumblr.api

import work.socialhub.ktumblr.api.request.auth.AuthAuthorizeUrlRequest
import work.socialhub.ktumblr.api.request.auth.AuthOAuth2TokenRefreshRequest
import work.socialhub.ktumblr.api.request.auth.AuthOAuth2TokenRequest
import work.socialhub.ktumblr.api.response.Response
import work.socialhub.ktumblr.api.response.auth.AuthOAuth2TokenResponse
import kotlin.js.JsExport

@JsExport
interface AuthResource {

    fun authorizeUrl(
        request: AuthAuthorizeUrlRequest
    ): String

    fun oAuth2Token(
        request: AuthOAuth2TokenRequest
    ): Response<AuthOAuth2TokenResponse>

    fun oAuth2TokenRefresh(
        request: AuthOAuth2TokenRefreshRequest
    ): Response<AuthOAuth2TokenResponse>
}