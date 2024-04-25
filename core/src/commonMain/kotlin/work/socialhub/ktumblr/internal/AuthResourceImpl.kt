package work.socialhub.ktumblr.internal

import io.ktor.http.*
import work.socialhub.khttpclient.HttpRequest
import work.socialhub.kmpcommon.runBlocking
import work.socialhub.ktumblr.TumblrAuth
import work.socialhub.ktumblr.api.AuthResource
import work.socialhub.ktumblr.api.request.auth.AuthAuthorizeUrlRequest
import work.socialhub.ktumblr.api.request.auth.AuthOAuth2TokenRefreshRequest
import work.socialhub.ktumblr.api.request.auth.AuthOAuth2TokenRequest
import work.socialhub.ktumblr.api.response.Response
import work.socialhub.ktumblr.api.response.auth.AuthOAuth2TokenResponse

class AuthResourceImpl(
    auth: TumblrAuth
) : AuthResource,
    AbstractResourceImpl(auth) {

    override fun authorizeUrl(
        request: AuthAuthorizeUrlRequest
    ): String {
        return URLBuilder().apply {
            protocol = URLProtocol.HTTPS
            host = "www.tumblr.com"
            encodedPath = "/oauth2/authorize"
            parameters.apply {
                append("client_id", auth.clientId)
                append("response_type", request.responseType)
                append("state", request.state!!)
                append("scope", request.scope)

                request.redirectUri?.let {
                    append("redirect_uri", it)
                }
            }
        }.build().toString()
    }


    override fun oAuth2Token(
        request: AuthOAuth2TokenRequest
    ): Response<AuthOAuth2TokenResponse> {
        return runBlocking {
            proceed<AuthOAuth2TokenResponse> {
                HttpRequest()
                    .url("https://api.tumblr.com/v2/oauth2/token")
                    .params(request.toMap())
                    .post()
            }
        }
    }

    override fun oAuth2TokenRefresh(
        request: AuthOAuth2TokenRefreshRequest
    ): Response<AuthOAuth2TokenResponse> {
        return runBlocking {
            proceed<AuthOAuth2TokenResponse> {
                HttpRequest()
                    .url("https://api.tumblr.com/v2/oauth2/token")
                    .params(request.toMap())
                    .post()
            }
        }
    }
}