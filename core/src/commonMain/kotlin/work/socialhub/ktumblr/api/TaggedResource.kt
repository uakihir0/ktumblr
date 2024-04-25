package work.socialhub.ktumblr.api

import work.socialhub.ktumblr.api.request.tagged.TaggedRequest
import work.socialhub.ktumblr.api.response.Body
import work.socialhub.ktumblr.api.response.Response
import work.socialhub.ktumblr.entity.post.Post

interface TaggedResource {

    /**
     * Tagged posts
     */
    fun tagged(
        request: TaggedRequest
    ): Response<Body<Array<Post>>>
}