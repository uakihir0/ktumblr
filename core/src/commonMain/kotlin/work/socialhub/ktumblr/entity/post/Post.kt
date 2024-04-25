package work.socialhub.ktumblr.entity.post

import work.socialhub.ktumblr.entity.Resource
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlin.js.JsExport

/**
 * This class is the base of all post types on Tumblr
 */

@JsExport
@Serializable
open class Post : Resource() {

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
