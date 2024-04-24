package work.socialhub.ktumblr.api.request.user

import work.socialhub.ktumblr.api.request.MapRequest
import kotlin.js.JsExport

@JsExport
class UserFollowingRequest : MapRequest {
    var limit: Int? = null
    var offset: Int? = null

    @JsExport.Ignore
    override fun toMap() =
        mutableMapOf<String, Any>().also {
            it.addParam("limit", limit)
            it.addParam("offset", offset)
        }
}