package work.socialhub.ktumblr.internal

import work.socialhub.ktumblr.TumblrAuth
import work.socialhub.ktumblr.api.TaggedResource
import work.socialhub.ktumblr.api.request.tagged.TaggedRequest
import work.socialhub.ktumblr.api.response.Body
import work.socialhub.ktumblr.api.response.Response
import work.socialhub.ktumblr.entity.post.Post

class TaggedResourceImpl(
    auth: TumblrAuth
) : TaggedResource,
    AbstractResourceImpl(auth) {

    override fun tagged(
        request: TaggedRequest
    ): Response<Body<Array<Post>>> {
        return oauthGet(
            "/tagged",
            request.toMap()
        )
    }
}