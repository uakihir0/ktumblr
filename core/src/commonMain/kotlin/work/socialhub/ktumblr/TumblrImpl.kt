package work.socialhub.ktumblr

import work.socialhub.ktumblr.api.BlogResource
import work.socialhub.ktumblr.api.TaggedResource
import work.socialhub.ktumblr.api.UserResource
import work.socialhub.ktumblr.internal.BlogResourceImpl
import work.socialhub.ktumblr.internal.TaggedResourceImpl
import work.socialhub.ktumblr.internal.UserResourceImpl

class TumblrImpl(
    private val auth: TumblrAuth
) : Tumblr {

    private val user: UserResource = UserResourceImpl(auth)
    private val blog: BlogResource = BlogResourceImpl(auth)
    private val tagged: TaggedResource = TaggedResourceImpl(auth)

    override fun user() = user
    override fun blog() = blog
    override fun tagged() = tagged
}