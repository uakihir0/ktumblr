package work.socialhub.ktumblr

object TumblrFactory {

    /**
     * get request instance
     */
    fun instance(
        clientId: String = "",
        clientSecret: String = "",
        accessToken: String? = null,
        refreshToken: String? = null,
    ): Tumblr {
        return TumblrImpl(
            TumblrAuth(
                clientId,
                clientSecret,
                accessToken,
                refreshToken
            )
        )
    }
}