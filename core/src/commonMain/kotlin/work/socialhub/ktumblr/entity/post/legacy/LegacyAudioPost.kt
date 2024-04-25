package work.socialhub.ktumblr.entity.post.legacy

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import work.socialhub.ktumblr.entity.post.Post
import kotlin.js.JsExport

@JsExport
@Serializable
class LegacyAudioPost(
    override var blogName: String?,
    override var idString: String?,
    override var genesisPostId: String?,
    override var postUrl: String?,
    override var parentPostUrl: String?,
    override var type: String?,
    override var timestamp: Int?,
    override var date: String?,
    override var format: String?,
    override var reblogKey: String?,
    override var tags: Array<String>?,
    override var isBookmarklet: Boolean?,
    override var isMobile: Boolean?,
    override var sourceUrl: String?,
    override var sourceTitle: String?,
    override var isLiked: Boolean?,
    override var state: String?,
    override var title: String?,
    override var body: String?
) : Post() {

    @SerialName("caption")
    var caption: String? = null

    // TODO
}