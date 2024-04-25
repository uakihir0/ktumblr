package work.socialhub.ktumblr.api.response.blog

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import work.socialhub.ktumblr.entity.user.FollowerUser
import kotlin.js.JsExport

@JsExport
@Serializable
class BlogFollowersResponse {

    @SerialName("total_users")
    var totalUsers: Int? = null

    @SerialName("users")
    var users: Array<FollowerUser>? = null
}