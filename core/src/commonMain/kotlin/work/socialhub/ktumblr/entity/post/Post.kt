package work.socialhub.ktumblr.entity.post

import kotlinx.serialization.Serializable
import work.socialhub.ktumblr.entity.Note
import work.socialhub.ktumblr.entity.blog.Blog
import work.socialhub.ktumblr.entity.blog.Reblog
import work.socialhub.ktumblr.entity.post.legacy.LegacyAnswerPost
import work.socialhub.ktumblr.entity.post.legacy.LegacyLinkPost
import work.socialhub.ktumblr.entity.post.legacy.LegacyPhotoPost
import work.socialhub.ktumblr.entity.post.legacy.LegacyQuotePost
import work.socialhub.ktumblr.entity.post.legacy.LegacyTextPost
import work.socialhub.ktumblr.entity.post.legacy.LegacyVideoPost
import work.socialhub.ktumblr.entity.trail.Trail
import work.socialhub.ktumblr.util.json.PostSerializer
import kotlin.js.JsExport

/**
 * This class is the base of all post types on Tumblr
 */

@JsExport
@Serializable(with = PostSerializer::class)
abstract class Post {

    val asLegacyTextPost get() = this as? LegacyTextPost
    val asLegacyPhotoPost get() = this as? LegacyPhotoPost
    val asLegacyQuotePost get() = this as? LegacyQuotePost
    val asLegacyLinkPost get() = this as? LegacyLinkPost
    val asLegacyVideoPost get() = this as? LegacyVideoPost
    val asLegacyAnswerPost get() = this as? LegacyAnswerPost

    abstract var blogName: String?
    abstract var idString: String?
    abstract var genesisPostId: String?
    abstract var postUrl: String?
    abstract var parentPostUrl: String?
    abstract var type: String?
    abstract var timestamp: Int?
    abstract var date: String?
    abstract var format: String?
    abstract var reblogKey: String?
    abstract var tags: Array<String>?
    abstract var isBookmarklet: Boolean?
    abstract var isMobile: Boolean?
    abstract var sourceUrl: String?
    abstract var sourceTitle: String?
    abstract var isLiked: Boolean?
    abstract var state: String?

    abstract var title: String?
    abstract var body: String?

    /**
     * Undocumented
     */
    abstract var noteCount: Int?
    abstract var summary: String?
    abstract var slug: String?

    /**
     * ReblogInfo
     */
    abstract var rebloggedFromId: String?
    abstract var rebloggedFromUrl: String?
    abstract var rebloggedFromName: String?
    abstract var rebloggedFromTitle: String?
    abstract var rebloggedFromUuid: String?
    abstract var rebloggedFromCanMessage: Boolean?
    abstract var rebloggedFromFollowing: Boolean?
    abstract var rebloggedRootId: String?
    abstract var rebloggedRootUrl: String?
    abstract var rebloggedRootName: String?
    abstract var rebloggedRootTitle: String?
    abstract var rebloggedRootUuid: String?
    abstract var rebloggedRootCanMessage: Boolean?
    abstract var rebloggedRootFollowing: Boolean?


    abstract var notes: Array<Note>?
    abstract var blog: Blog?
    abstract var reblog: Reblog?
    abstract var trail: Array<Trail>?

    /*
    @SerialName("author_id")
    var authorId: String? = null
    @SerialName("short_url")
    val shortUrl: String? = null
    */
}
