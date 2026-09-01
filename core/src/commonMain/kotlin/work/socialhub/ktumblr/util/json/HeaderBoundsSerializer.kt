package work.socialhub.ktumblr.util.json

import kotlinx.serialization.builtins.serializer
import kotlinx.serialization.json.JsonArray
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.JsonPrimitive
import kotlinx.serialization.json.JsonTransformingSerializer

/**
 * Reads a blog theme's `header_bounds` whatever shape it arrives in.
 *
 * The v2 reference types this field as "Mixed": it is documented as "a
 * comma-separated list of top/right/bottom/left coordinates", but a blog whose
 * header is not cropped sends the number `0` or an empty string instead, and
 * both turn up in a single /v2/user/dashboard page. Declaring it as any one
 * type would fail the entire response — the page is decoded in one pass — so
 * every scalar is normalised to its string form.
 *
 * A JSON array of coordinates decodes to the same comma-separated string the
 * documentation describes; anything else (an object, say) decodes to an empty
 * string rather than failing the page.
 */
object HeaderBoundsSerializer : JsonTransformingSerializer<String>(
    String.serializer()
) {

    override fun transformDeserialize(
        element: JsonElement
    ): JsonElement {
        return when {
            element is JsonPrimitive -> JsonPrimitive(element.content)
            element is JsonArray -> JsonPrimitive(
                element.joinToString(",") { item ->
                    if (item is JsonPrimitive) item.content else ""
                }
            )

            else -> JsonPrimitive("")
        }
    }
}
