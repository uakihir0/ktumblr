package work.socialhub.ktumblr.internal

import work.socialhub.ktumblr.api.TaggedResource
import work.socialhub.ktumblr.api.request.tagged.TaggedRequest
import work.socialhub.ktumblr.api.response.Response
import work.socialhub.ktumblr.entity.post.Post

class TaggedResourceImpl :
    TaggedResource,
    AbstractResourceImpl() {

    override fun tagged(
        request: TaggedRequest
    ): Response<Array<Post>> {
        return oauthGet(
            "/tagged",
            request.toMap()
        )
    }
}