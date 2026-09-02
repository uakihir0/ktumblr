package work.socialhub.ktumblr.entity.blog

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import work.socialhub.ktumblr.util.json.HeaderBoundsSerializer
import kotlin.js.JsExport

@JsExport
@Serializable
class BlogTheme {

    @SerialName("avatar_shape")
    var avatarShape: String? = null

    @SerialName("background_color")
    var backgroundColor: String? = null

    @SerialName("body_font")
    var bodyFont: String? = null

    /**
     * Comma-separated top/right/bottom/left crop coordinates for the header.
     *
     * The reference types this as "Mixed" and blogs without a cropped header
     * send `0` or `""`, so it is read through [HeaderBoundsSerializer].
     */
    @SerialName("header_bounds")
    @Serializable(with = HeaderBoundsSerializer::class)
    var headerBounds: String? = null

    @SerialName("header_image")
    var headerImage: String? = null

    // TODO: header_image_npf
    // @SerialName("header_image_npf")
    // var headerImageNpf: String? = null

    @SerialName("header_image_focused")
    var headerImageFocused: String? = null

    @SerialName("header_image_poster")
    var headerImagePoster: String? = null

    @SerialName("header_image_scaled")
    var headerImageScaled: String? = null

    @SerialName("header_stretch")
    var isHeaderStretch: Boolean = false

    @SerialName("link_color")
    val linkColor: String? = null

    @SerialName("show_avatar")
    val isShowAvatar: Boolean = false

    @SerialName("show_description")
    val isShowDescription: Boolean = false

    @SerialName("show_header_image")
    val isShowHeaderImage: Boolean = false

    @SerialName("show_title")
    val isShowTitle: Boolean = false

    @SerialName("title_color")
    val titleColor: String? = null

    @SerialName("title_font")
    val titleFont: String? = null

    @SerialName("title_font_weight")
    val titleFontWeight: String? = null
}
