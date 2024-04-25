package work.socialhub.ktumblr.api.response

import kotlin.js.JsExport

@JsExport
class Body<T> {
    var meta: Meta? = null
    var response: T? = null
}