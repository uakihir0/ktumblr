package work.socialhub.ktumblr.api.response

import kotlin.js.JsExport

@JsExport
class ResponseUnit(
    var json: String,
    val status: Int? = null,
    val message: String? = null,
)