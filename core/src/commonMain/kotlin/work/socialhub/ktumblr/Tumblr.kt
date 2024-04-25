package work.socialhub.ktumblr

import work.socialhub.ktumblr.api.AuthResource
import work.socialhub.ktumblr.api.BlogResource
import work.socialhub.ktumblr.api.TaggedResource
import work.socialhub.ktumblr.api.UserResource
import kotlin.js.JsExport

@JsExport
interface Tumblr {

    fun auth(): AuthResource
    fun user(): UserResource
    fun blog(): BlogResource
    fun tagged(): TaggedResource
}