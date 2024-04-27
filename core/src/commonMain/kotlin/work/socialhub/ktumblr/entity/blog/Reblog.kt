package work.socialhub.ktumblr.entity.blog

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlin.js.JsExport

@JsExport
@Serializable
class Reblog {

    @SerialName("comment")
    var comment: String? = null

    @SerialName("tree_html")
    var treeHtml: String? = null
}