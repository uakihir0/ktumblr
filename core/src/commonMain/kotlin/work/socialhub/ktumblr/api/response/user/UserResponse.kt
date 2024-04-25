package work.socialhub.ktumblr.api.response.user

import kotlinx.serialization.Serializable
import work.socialhub.ktumblr.entity.user.User
import kotlin.js.JsExport

@JsExport
@Serializable
class UserResponse {
    var user: User? = null
}