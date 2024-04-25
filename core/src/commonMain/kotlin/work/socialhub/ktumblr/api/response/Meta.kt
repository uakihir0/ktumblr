package work.socialhub.ktumblr.api.response

import kotlinx.serialization.Serializable
import kotlin.js.JsExport

@JsExport
@Serializable
class Meta {
    var status: Int? = null
    var msg: String? = null
}