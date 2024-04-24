package work.socialhub.ktumblr.api.request.user

import work.socialhub.ktumblr.api.request.MapRequest

class UserFollowRequest : MapRequest {
    var url: String? = null
    var email: String? = null

    override fun toMap() =
        mutableMapOf<String, Any>().also {
            it.addParam("url", url)
            it.addParam("email", email)
        }
}