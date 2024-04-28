package work.socialhub.ktumblr.entity

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlin.js.JsExport

@JsExport
@Serializable
class NoteAvatar {

    @SerialName("64")
    var size64: String? = null
    @SerialName("128")
    var size128: String? = null
}