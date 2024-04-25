package work.socialhub.ktumblr.apis

import work.socialhub.ktumblr.AbstractTest
import work.socialhub.ktumblr.api.request.auth.AuthAuthorizeUrlRequest
import work.socialhub.ktumblr.api.request.auth.AuthOAuth2TokenRequest
import kotlin.test.Test

class AuthTest : AbstractTest() {

    @Test
    fun testAuthorizeUrl() {
        val response = tumblr().auth().authorizeUrl(
            AuthAuthorizeUrlRequest().also {
                it.state = "ktumblr"
            }
        )
        println(response)
    }

    @Test
    fun testOAuth2Token() {
        val response = tumblr().auth().oAuth2Token(
            AuthOAuth2TokenRequest().also {
                it.clientId = CLIENT_ID
                it.clientSecret = CLIENT_SECRET
                it.code = "{{CODE}}"
            }
        )

        println("AccessToken  > ${response.data.accessToken}")
        println("RefreshToken > ${response.data.refreshToken}")
        println("ExpiresIn    > ${response.data.expiresIn}")

        saveTokens(
            response.data.accessToken!!,
            response.data.refreshToken!!,
        )
    }
}