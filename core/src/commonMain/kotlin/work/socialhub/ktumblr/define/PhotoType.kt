package work.socialhub.ktumblr.define

import kotlin.js.JsExport

@JsExport
enum class PhotoType(
    val prefix: String
) {
    SOURCE("source"),
    FILE("data")
}
