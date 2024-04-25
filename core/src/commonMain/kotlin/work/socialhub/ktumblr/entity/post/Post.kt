package work.socialhub.ktumblr.entity.post

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import work.socialhub.ktumblr.entity.Resource
import work.socialhub.ktumblr.entity.post.legacy.LegacyAnswerPost
import work.socialhub.ktumblr.entity.post.legacy.LegacyLinkPost
import work.socialhub.ktumblr.entity.post.legacy.LegacyPhotoPost
import work.socialhub.ktumblr.entity.post.legacy.LegacyQuotePost
import work.socialhub.ktumblr.entity.post.legacy.LegacyTextPost
import work.socialhub.ktumblr.entity.post.legacy.LegacyVideoPost
import work.socialhub.ktumblr.util.json.PostSerializer
import kotlin.js.JsExport

/**
 * This class is the base of all post types on Tumblr
 */

@JsExport
@Serializable(with = PostSerializer::class)
open class Post : Resource() {

    val asLegacyTextPost get() = this as? LegacyTextPost
    val asLegacyPhotoPost get() = this as? LegacyPhotoPost
    val asLegacyQuotePost get() = this as? LegacyQuotePost
    val asLegacyLinkPost get() = this as? LegacyLinkPost
    val asLegacyVideoPost get() = this as? LegacyVideoPost
    val asLegacyAnswerPost get() = this as? LegacyAnswerPost

    @SerialName("blog_name")
    var blogName: String? = null

    @SerialName("id_stirng")
    var idString: String? = null

    @SerialName("genesis_post_id")
    var genesisPostId: String? = null

    @SerialName("post_url")
    var postUrl: String? = null

    @SerialName("parent_post_url")
    var parentPostUrl: String? = null

    @SerialName("type")
    var type: String? = null

    @SerialName("timestamp")
    var timestamp: Int? = null

    @SerialName("date")
    var date: String? = null

    @SerialName("format")
    var format: String? = null

    @SerialName("reblog_key")
    var reblogKey: String? = null

    @SerialName("tags")
    var tags: Array<String>? = null

    @SerialName("bookmarklet")
    var isBookmarklet: Boolean? = null

    @SerialName("mobile")
    var isMobile: Boolean? = null

    @SerialName("source_url")
    var sourceUrl: String? = null

    @SerialName("source_title")
    var sourceTitle: String? = null

    @SerialName("liked")
    var isLiked: Boolean? = null

    @SerialName("state")
    var state: String? = null

    /*
    @SerialName("author_id")
    var authorId: String? = null
    @SerialName("short_url")
    val shortUrl: String? = null
    @SerialName("slug")
    var slug: String? = null
    */

    @SerialName("title")
    var title: String? = null

    @SerialName("body")
    var body: String? = null
}
