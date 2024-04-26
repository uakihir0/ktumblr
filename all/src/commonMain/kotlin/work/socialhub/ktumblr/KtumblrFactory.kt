package work.socialhub.ktumblr

object KtumblrFactory {

    /**
     * get request instance
     */
    fun instance(
        clientId: String = "",
        clientSecret: String? = null,
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