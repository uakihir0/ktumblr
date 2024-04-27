package work.socialhub.ktumblr.apis

import work.socialhub.ktumblr.AbstractTest
import work.socialhub.ktumblr.Printer.dumpPosts
import work.socialhub.ktumblr.api.request.tagged.TaggedRequest
import kotlin.test.Test

class TaggedTest : AbstractTest() {

    @Test
    fun test() {
        val response = tumblr().tagged().tagged(
            TaggedRequest().also {
                it.tag = "SocialHub"
            }
        )
        println(response.json)
        dumpPosts(response.data.response!!)
    }
}