package work.socialhub.ktumblr.entity.post.legacy

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import work.socialhub.ktumblr.entity.blog.Blog
import work.socialhub.ktumblr.entity.blog.Reblog
import work.socialhub.ktumblr.entity.post.Post
import work.socialhub.ktumblr.entity.trail.Trail
import kotlin.js.JsExport

/**
 * This class represents a Post of type "answer"
 */
@JsExport
@Serializable
class LegacyAnswerPost(
    @SerialName("blog_name")
    override var blogName: String?,
    @SerialName("id_string")
    override var idString: String?,
    @SerialName("genesis_post_id")
    override var genesisPostId: String?,
    @SerialName("post_url")
    override var postUrl: String?,
    @SerialName("parent_post_url")
    override var parentPostUrl: String?,
    @SerialName("type")
    override var type: String?,
    @SerialName("timestamp")
    override var timestamp: Int?,
    @SerialName("date")
    override var date: String?,
    @SerialName("format")
    override var format: String?,
    @SerialName("reblog_key")
    override var reblogKey: String?,
    @SerialName("tags")
    override var tags: Array<String>?,
    @SerialName("bookmarklet")
    override var isBookmarklet: Boolean?,
    @SerialName("mobile")
    override var isMobile: Boolean?,
    @SerialName("source_url")
    override var sourceUrl: String?,
    @SerialName("source_title")
    override var sourceTitle: String?,
    @SerialName("liked")
    override var isLiked: Boolean?,
    @SerialName("state")
    override var state: String?,
    @SerialName("title")
    override var title: String?,
    @SerialName("body")
    override var body: String?,
    @SerialName("blog")
    override var blog: Blog?,
    @SerialName("reblog")
    override var reblog: Reblog?,
    @SerialName("trail")
    override var trail: Array<Trail>?
) : Post() {

    @SerialName("asking_name")
    val askingName: String? = null

    @SerialName("asking_url")
    val askingUrl: String? = null

    @SerialName("question")
    val question: String? = null

    @SerialName("answer")
    val answer: String? = null


}
