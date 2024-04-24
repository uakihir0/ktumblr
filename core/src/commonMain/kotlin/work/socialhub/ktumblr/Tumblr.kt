package work.socialhub.ktumblr

import work.socialhub.ktumblr.api.BlogResource
import work.socialhub.ktumblr.api.TaggedResource
import work.socialhub.ktumblr.api.UserResource

interface Tumblr {

    fun user(): UserResource
    fun blog(): BlogResource
    fun tagged(): TaggedResource
}