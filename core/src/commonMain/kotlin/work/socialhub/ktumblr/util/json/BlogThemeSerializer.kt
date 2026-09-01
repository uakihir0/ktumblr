package work.socialhub.ktumblr.util.json

import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.JsonTransformingSerializer
import work.socialhub.ktumblr.entity.blog.BlogTheme

/**
 * Tolerates a `theme` that the API sends as an empty array.
 *
 * Tumblr serializes an empty associative array as `[]` rather than `{}`, so a
 * blog with no theme arrives as `"theme": []`. kotlinx.serialization rejects
 * that with `Expected JsonObject, but had JsonArray`, and because a response is
 * decoded in a single pass, one such blog fails the *entire* response — on
 * /v2/user/dashboard every post in the page is lost, not just the one carrying
 * it. A single themeless blog anywhere in a trail was enough to blank a whole
 * timeline.
 *
 * Anything that is not a JSON object decodes as an empty [BlogTheme]: all of its
 * properties are optional, so callers see the same nulls they would for a blog
 * with no theme at all.
 */
object BlogThemeSerializer : JsonTransformingSerializer<BlogTheme>(
    BlogTheme.serializer()
) {

    override fun transformDeserialize(
        element: JsonElement
    ): JsonElement {
        return if (element is JsonObject) element
        else JsonObject(emptyMap())
    }
}
