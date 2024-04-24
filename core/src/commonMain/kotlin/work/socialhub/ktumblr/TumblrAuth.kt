package work.socialhub.ktumblr

class TumblrAuth(
    var clientId: String,
    var clientSecret: String,
    var accessToken: String? = null,
    var refreshToken: String? = null,
) {

    fun oAuthBearerToken(): String {
        checkNotNull(accessToken)
        return "Bearer $accessToken"
    }
}