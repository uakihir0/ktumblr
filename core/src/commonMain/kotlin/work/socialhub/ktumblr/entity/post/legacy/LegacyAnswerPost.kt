package work.socialhub.ktumblr.entity.post.legacy

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import work.socialhub.ktumblr.entity.post.Post
import kotlin.js.JsExport

/**
 * This class represents a Post of type "answer"
 */
@JsExport
@Serializable
class LegacyAnswerPost : Post() {

    @SerialName("asking_name")
    val askingName: String? = null

    @SerialName("asking_url")
    val askingUrl: String? = null

    @SerialName("question")
    val question: String? = null

    @SerialName("answer")
    val answer: String? = null
}
