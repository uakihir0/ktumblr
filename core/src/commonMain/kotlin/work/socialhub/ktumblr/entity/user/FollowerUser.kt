package work.socialhub.ktumblr.entity.user

import kotlinx.serialization.Serializable
import kotlin.js.JsExport

@JsExport
@Serializable
class FollowerUser {

    var name: String? = null
    var following: Boolean? = null
    var url: String? = null
    var updated: String? = null
}