package work.socialhub.ktumblr.api.request.user

import work.socialhub.ktumblr.api.request.MapRequest

class UserUnlikeRequest : MapRequest {
    var id: String? = null
    var reblogKey: String? = null

    override fun toMap() =
        mutableMapOf<String, Any>().also {
            it.addParam("id", id)
            it.addParam("reblog_key", reblogKey)
        }
}