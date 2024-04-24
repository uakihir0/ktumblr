package work.socialhub.ktumblr.api.request.user

import work.socialhub.ktumblr.api.request.MapRequest

class UserUnfollowRequest : MapRequest {
    var url: String? = null

    override fun toMap() =
        mutableMapOf<String, Any>().also {
            it.addParam("url", url)
        }
}