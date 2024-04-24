package work.socialhub.ktumblr.api.request.user

import work.socialhub.ktumblr.api.request.MapRequest

class UserLikesRequest : MapRequest {
    var limit: Int? = null
    var offset: Int? = null
    var before: Int? = null
    var after: Int? = null

    override fun toMap() =
        mutableMapOf<String, Any>().also {
            it.addParam("limit", limit)
            it.addParam("offset", offset)
            it.addParam("before", before)
            it.addParam("after", after)
        }
}