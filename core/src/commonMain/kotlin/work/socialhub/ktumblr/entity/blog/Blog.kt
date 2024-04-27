package work.socialhub.ktumblr.entity.blog

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import work.socialhub.ktumblr.entity.Resource
import kotlin.js.JsExport

/**
 * This class represents an individual Tumbelog
 */
@JsExport
@Serializable
class Blog: Resource() {

    @SerialName("title")
    var title: String? = null

    @SerialName("posts")
    var postCount: Int = 0

    @SerialName("name")
    var name: String? = null

    @SerialName("updated")
    var updated: Int? = null

    @SerialName("description")
    var description: String? = null

    @SerialName("ask")
    var isAsk: Boolean? = null

    @SerialName("ask_anon")
    var isAskAnon: Boolean? = null

    @SerialName("followed")
    var isFollowed: Boolean? = null

    @SerialName("likes")
    val likeCount: Int? = null

    @SerialName("is_blocked_from_primary")
    val isBlockedFromPrimary: Boolean? = null

    @SerialName("avatar")
    val avatar: Array<BlogAvatar>? = null

    @SerialName("url")
    val url: String? = null

    @SerialName("theme")
    val theme: BlogTheme? = null

    /**
     * Undocumented Fields
     */
    @SerialName("admin")
    val isAdmin: Boolean? = null

    @SerialName("ask_page_title")
    val askPageTitle: String? = null

    @SerialName("is_nsfw")
    val isNSFW: Boolean? = null

    @SerialName("primary")
    val isPrimary: Boolean? = null

    @SerialName("followers")
    val followerCount: Int? = null
}
