package work.socialhub.ktumblr.util.json

import kotlinx.serialization.DeserializationStrategy
import kotlinx.serialization.json.JsonContentPolymorphicSerializer
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.JsonPrimitive
import kotlinx.serialization.json.jsonObject
import work.socialhub.ktumblr.entity.post.Post
import work.socialhub.ktumblr.entity.post.legacy.LegacyAnswerPost
import work.socialhub.ktumblr.entity.post.legacy.LegacyAudioPost
import work.socialhub.ktumblr.entity.post.legacy.LegacyChatPost
import work.socialhub.ktumblr.entity.post.legacy.LegacyLinkPost
import work.socialhub.ktumblr.entity.post.legacy.LegacyPhotoPost
import work.socialhub.ktumblr.entity.post.legacy.LegacyPostcardPost
import work.socialhub.ktumblr.entity.post.legacy.LegacyQuotePost
import work.socialhub.ktumblr.entity.post.legacy.LegacyTextPost
import work.socialhub.ktumblr.entity.post.legacy.LegacyVideoPost

object PostSerializer :
    JsonContentPolymorphicSerializer<Post>(
        Post::class
    ) {

    override fun selectDeserializer(
        element: JsonElement
    ): DeserializationStrategy<Post> {

        val type = element.jsonObject["type"]
        return if (type is JsonPrimitive && type.isString) {
            when (type.content) {
                "text" -> LegacyTextPost.serializer()
                "photo" -> LegacyPhotoPost.serializer()
                "quote" -> LegacyQuotePost.serializer()
                "link" -> LegacyLinkPost.serializer()
                "video" -> LegacyVideoPost.serializer()
                "audio" -> LegacyAudioPost.serializer()
                "chat" -> LegacyChatPost.serializer()
                "answer" -> LegacyAnswerPost.serializer()
                "postcard" -> LegacyPostcardPost.serializer()
                else -> fallbackSerializer()
            }
        } else fallbackSerializer()
    }

    /**
     * Deserializer used when the post type is missing or not one of the legacy
     * types above (Tumblr keeps introducing new NPF-only types).
     *
     * This must NOT be `Post.serializer()`: `Post` is annotated
     * `@Serializable(with = PostSerializer::class)`, so `Post.serializer()`
     * resolves back to this very serializer and the lookup recurses until the
     * stack overflows — taking down the whole response, not just the one post.
     *
     * [LegacyTextPost] is the safe stand-in: every one of its properties is
     * nullable, and `Json { explicitNulls = false }` treats missing nullable
     * properties as null, so any post object decodes into it. Only the fields
     * shared by all post types (id, blog, timestamp, trail, ...) are populated;
     * the type-specific payload is dropped.
     */
    private fun fallbackSerializer(): DeserializationStrategy<Post> {
        return LegacyTextPost.serializer()
    }
}