package work.socialhub.ktumblr.util.json

import kotlinx.serialization.DeserializationStrategy
import kotlinx.serialization.json.JsonContentPolymorphicSerializer
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.JsonPrimitive
import kotlinx.serialization.json.jsonObject
import work.socialhub.ktumblr.entity.post.Post
import work.socialhub.ktumblr.entity.post.legacy.LegacyAnswerPost
import work.socialhub.ktumblr.entity.post.legacy.LegacyLinkPost
import work.socialhub.ktumblr.entity.post.legacy.LegacyPhotoPost
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
                "answer" -> LegacyAnswerPost.serializer()
                else -> Post.serializer()
            }
        } else Post.serializer()
    }
}