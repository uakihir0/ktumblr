package work.socialhub.ktumblr.internal

import work.socialhub.ktumblr.TumblrAuth
import work.socialhub.ktumblr.api.TaggedResource
import work.socialhub.ktumblr.api.request.tagged.TaggedRequest
import work.socialhub.ktumblr.api.response.Body
import work.socialhub.ktumblr.api.response.Response
import work.socialhub.ktumblr.entity.post.Post
import work.socialhub.ktumblr.util.toBlocking

class TaggedResourceImpl(
    auth: TumblrAuth
) : TaggedResource,
    AbstractResourceImpl(auth) {

    override suspend fun tagged(
        request: TaggedRequest
    ): Response<Body<Array<Post>>> {
        return apiKeyGet(
            "/tagged",
            request.toMap()
        )
    }

    override fun taggedBlocking(
        request: TaggedRequest
    ): Response<Body<Array<Post>>> = toBlocking { tagged(request) }
}
