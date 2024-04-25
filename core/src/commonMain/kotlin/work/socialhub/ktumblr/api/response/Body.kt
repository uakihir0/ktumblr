package work.socialhub.ktumblr.api.response

import kotlinx.serialization.Serializable
import kotlin.js.JsExport

@JsExport
@Serializable
class Body<T> {
    var meta: Meta? = null
    var response: T? = null
}