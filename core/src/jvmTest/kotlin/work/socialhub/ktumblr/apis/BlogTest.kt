package work.socialhub.ktumblr.apis

import work.socialhub.ktumblr.AbstractTest
import work.socialhub.ktumblr.Printer.dump
import work.socialhub.ktumblr.Printer.dumpPosts
import work.socialhub.ktumblr.api.request.blog.BlogAvatarRequest
import work.socialhub.ktumblr.api.request.blog.BlogInfoRequest
import work.socialhub.ktumblr.api.request.blog.BlogLikesRequest
import kotlin.test.Test

class BlogTest: AbstractTest() {

    @Test
    fun testBlogInfo() {
        val blog = checkToken {
            tumblr().blog().blogInfo(
                BlogInfoRequest().also {
                    it.blogName = "staff"
                }
            )
        }

        println(blog.json)
        dump(blog.data.response?.blog!!)
    }

    @Test
    fun testBlogAvatar() {
        val avatar = checkToken {
            tumblr().blog().blogAvatar(
                BlogAvatarRequest().also {
                    it.blogName = "uakihiro"
                }
            )
        }

        println(avatar.json)
        println(avatar.data)
    }

    @Test
    fun testBlogLikes() {
        val likes = checkToken {
            tumblr().blog().blogLikes(
                BlogLikesRequest().also {
                    it.blogName = "uakihiro"
                }
            )
        }

        println(likes.json)
        dumpPosts(likes.data.response?.likedPosts!!)
    }
}