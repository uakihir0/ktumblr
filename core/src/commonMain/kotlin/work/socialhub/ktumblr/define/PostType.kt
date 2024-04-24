package work.socialhub.ktumblr.define

import kotlin.js.JsExport

@JsExport
enum class PostType(
    val value: String
) {
    TEXT("text"),
    PHOTO("photo"),
    QUOTE("quote"),
    LINK("link"),
    CHAT("chat"),
    AUDIO("audio"),
    VIDEO("video"),
    ANSWER("answer"),
    POSTCARD("postcard"),
    UNKNOWN("unknown")
}
