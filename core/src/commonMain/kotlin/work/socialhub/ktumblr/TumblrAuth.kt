package work.socialhub.ktumblr

class TumblrAuth {
    var clientId: String = ""
    var clientSecret: String = ""
    var accessToken: String = ""
    var refreshToken: String = ""

    fun oAuthBearerToken(): String {
        return "Bearer $accessToken"
    }
}