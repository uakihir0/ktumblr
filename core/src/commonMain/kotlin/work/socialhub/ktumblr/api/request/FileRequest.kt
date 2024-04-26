package work.socialhub.ktumblr.api.request

import kotlin.js.JsExport

@JsExport
class FileRequest(
    val name: String,
    val data: ByteArray,
)