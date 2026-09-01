package work.socialhub.ktumblr.entity.trail

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import work.socialhub.ktumblr.entity.blog.BlogTheme
import work.socialhub.ktumblr.util.json.BlogThemeSerializer
import kotlin.js.JsExport

@JsExport
@Serializable
class BlogInTrail {

    @SerialName("name")
    val name: String? = null

    /**
     * Unique identifier of the blog (for example `t:0aY0xL2Fi1OFJg4YxpmegQ`),
     * usable in place of a blog name in any request.
     *
     * The specification says a trail blog carries "at least a `uuid` field", but
     * the legacy trail sends only the name — so this is null on every trail item
     * a default request returns.
     */
    @SerialName("uuid")
    val uuid: String? = null

    @SerialName("active")
    val isActive: Boolean = false

    // Deactivated blogs arrive with `"theme": []` — see BlogThemeSerializer.
    @SerialName("theme")
    @Serializable(with = BlogThemeSerializer::class)
    val theme: BlogTheme? = null

    @SerialName("share_likes")
    val isShareLikes: Boolean = false

    @SerialName("share_following")
    val isShareFollowing: Boolean = false

    @SerialName("can_be_followed")
    val isCanBeFollowed: Boolean = false
}
