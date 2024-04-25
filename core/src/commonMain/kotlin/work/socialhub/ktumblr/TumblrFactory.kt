package work.socialhub.ktumblr

import kotlin.js.JsExport

@JsExport
object TumblrFactory {

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