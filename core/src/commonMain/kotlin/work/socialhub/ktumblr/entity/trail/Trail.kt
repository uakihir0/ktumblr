package work.socialhub.ktumblr.entity.trail

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlin.js.JsExport

/**
 * An item of a post's reblog trail.
 *
 * These are the fields of the legacy trail, which is what every endpoint returns
 * by default. The NPF trail (`npf=true`) sends `content` and `layout` as arrays
 * instead, which this class does not model yet.
 */
@JsExport
@Serializable
class Trail {

    /** Absent on a broken trail item; see [brokenBlogName]. */
    @SerialName("blog")
    val blog: BlogInTrail? = null

    /** Absent on a broken trail item: the original post is gone. */
    @SerialName("post")
    val post: IdInTrail? = null

    @SerialName("content_raw")
    val contentRaw: String? = null

    @SerialName("content")
    val content: String? = null

    /**
     * Name of the blog of a "broken" trail item.
     *
     * A trail item is broken when the original post or its blog has been
     * deleted or suspended, in which case the name is all that is left: there
     * is no [blog] object and no post ID. The specification used to describe a
     * `broken_blog` object with a name and an avatar here, but Tumblr stopped
     * sending it, so the name is the only field to rely on.
     */
    @SerialName("broken_blog_name")
    val brokenBlogName: String? = null

    // The API sends these as `is_current_item` / `is_root_item`. They used to be
    // declared without the prefix, so both always decoded as false and callers
    // could never tell which trail entry the post itself contributed.
    @SerialName("is_current_item")
    val isCurrentItem: Boolean = false

    @SerialName("is_root_item")
    val isRootItem: Boolean = false
}
