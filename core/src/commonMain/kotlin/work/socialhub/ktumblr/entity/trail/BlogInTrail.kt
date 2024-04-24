package work.socialhub.ktumblr.entity.trail

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import work.socialhub.ktumblr.entity.blog.BlogTheme
import kotlin.js.JsExport

@JsExport
@Serializable
class BlogInTrail {

    @SerialName("name")
    val name: String? = null

    @SerialName("active")
    val isActive: Boolean = false

    @SerialName("theme")
    val theme: Array<BlogTheme>? = null

    @SerialName("share_likes")
    val isShareLikes: Boolean = false

    @SerialName("share_following")
    val isShareFollowing: Boolean = false

    @SerialName("can_be_followed")
    val isCanBeFollowed: Boolean = false
}
