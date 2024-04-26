package work.socialhub.ktumblr.apis

import work.socialhub.ktumblr.AbstractTest
import work.socialhub.ktumblr.Printer.dump
import work.socialhub.ktumblr.Printer.dumpPosts
import work.socialhub.ktumblr.api.request.FileRequest
import work.socialhub.ktumblr.api.request.blog.BlogAvatarRequest
import work.socialhub.ktumblr.api.request.blog.BlogInfoRequest
import work.socialhub.ktumblr.api.request.blog.BlogLikesRequest
import work.socialhub.ktumblr.api.request.blog.BlogPostsRequest
import work.socialhub.ktumblr.api.request.blog.post.BlogPhotoPostRequest
import work.socialhub.ktumblr.api.request.blog.post.BlogTextPostRequest
import work.socialhub.ktumblr.define.PostType
import kotlin.io.encoding.Base64
import kotlin.io.encoding.ExperimentalEncodingApi
import kotlin.test.Test

class BlogTest : AbstractTest() {

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
    fun testBlogPosts() {
        val blog = checkToken {
            tumblr().blog().blogPosts(
                BlogPostsRequest().also {
                    it.blogName = "uakihiro"
                }
            )
        }

        println(blog.json)
        dumpPosts(blog.data.response?.posts!!)
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

    @Test
    fun testBlogPostCreate() {
        checkToken {
            tumblr().blog().postCreate(
                BlogTextPostRequest().also {
                    it.blogName = "uakihiro"
                    it.state = "state"
                    it.type = PostType.TEXT.value
                    it.title = "test"
                    it.body = "test"
                }
            )
        }
    }

    @Test
    @OptIn(ExperimentalEncodingApi::class)
    fun testBlogPostCreateWithPhoto() {
        val stream = javaClass.getResourceAsStream("/icon.png")
        val bytes = stream.readAllBytes()

        checkToken {
            tumblr().blog().postCreate(
                BlogPhotoPostRequest().also {
                    it.blogName = "uakihiro"
                    it.state = "state"
                    it.type = PostType.PHOTO.value
                    it.caption = "ktumblr test"
                    it.data64 = Base64.encode(bytes)
                }
            )
        }
    }

    @Test
    fun testBlogPostCreateWithMultiplePhoto() {
        val stream = javaClass.getResourceAsStream("/icon.png")
        val bytes = stream.readAllBytes()

        checkToken {
            tumblr().blog().postCreate(
                BlogPhotoPostRequest().also {
                    it.blogName = "uakihiro"
                    it.state = "state"
                    it.type = PostType.PHOTO.value
                    it.caption = "ktumblr test"
                    it.data = arrayOf(
                        FileRequest("icon.png", bytes),
                    )
                }
            )
        }
    }
}