package work.socialhub.ktumblr

import work.socialhub.ktumblr.api.AuthResource
import work.socialhub.ktumblr.api.BlogResource
import work.socialhub.ktumblr.api.TaggedResource
import work.socialhub.ktumblr.api.UserResource
import work.socialhub.ktumblr.internal.AuthResourceImpl
import work.socialhub.ktumblr.internal.BlogResourceImpl
import work.socialhub.ktumblr.internal.TaggedResourceImpl
import work.socialhub.ktumblr.internal.UserResourceImpl

class TumblrImpl(
    private val tumblrAuth: TumblrAuth
) : Tumblr {

    private val auth: AuthResource = AuthResourceImpl(tumblrAuth)
    private val user: UserResource = UserResourceImpl(tumblrAuth)
    private val blog: BlogResource = BlogResourceImpl(tumblrAuth)
    private val tagged: TaggedResource = TaggedResourceImpl(tumblrAuth)

    override fun auth() = auth
    override fun user() = user
    override fun blog() = blog
    override fun tagged() = tagged
}