package work.socialhub.ktumblr

import work.socialhub.ktumblr.entity.blog.Blog
import work.socialhub.ktumblr.entity.post.Post
import work.socialhub.ktumblr.entity.post.legacy.LegacyLinkPost
import work.socialhub.ktumblr.entity.post.legacy.LegacyPhotoPost
import work.socialhub.ktumblr.entity.post.legacy.LegacyQuotePost
import work.socialhub.ktumblr.entity.post.legacy.LegacyVideoPost
import work.socialhub.ktumblr.entity.user.User

object Printer {

    fun AbstractTest.dump(
        user: User,
        sp: String = ""
    ) {
        println("${sp}=== User ===")
        println("${sp}Name > ${user.name}")
        user.blogs?.let { dumpBlogs(it, "$sp  ") }
    }

    fun AbstractTest.dump(
        blog: Blog,
        sp: String = ""
    ) {
        println("${sp}=== Blog ===")
        println("${sp}Title   > ${blog.title}")
        println("${sp}Name    > ${blog.name}")
        println("${sp}Url     > ${blog.url}")
        println("${sp}Avatar  > ${blog.avatar?.get(0)?.url}")
        println("${sp}Primary > ${blog.isPrimary}")
        println("")
    }

    fun AbstractTest.dump(
        post: Post,
        sp: String = ""
    ) {
        println("${sp}=== Post ===")
        println("${sp}Class > ${post::javaClass.get().name}")
        println("${sp}Blog  > ${post.blogName}")
        println("${sp}Type  > ${post.type}")
        println("${sp}Title > ${post.title}")
        println("${sp}Body  > ${post.body}")
        println("${sp}Url   > ${post.postUrl}")

        when (post) {
            is LegacyQuotePost -> {
                println("${sp}>> Quote")
                println("${sp}Text   > ${post.text}")
                println("${sp}Source > ${post.source}")
            }
            is LegacyLinkPost -> {
                println("${sp}>> Link")
                println("${sp}Url         > ${post.url}")
                println("${sp}Description > ${post.description}")
            }
            is LegacyPhotoPost -> {
                println("${sp}>> Photo")
                println("${sp}Caption  > ${post.caption}")
                post.photos?.forEachIndexed { i, photo ->
                    println("${sp}Photo[$i] > ${photo.originalSize?.url}")
                }
            }
            is LegacyVideoPost -> {
                println("${sp}>> Video")
                println("${sp}Caption  > ${post.caption}")
                post.player?.forEachIndexed { i, player ->
                    println("${sp}Player[$i] > ${player.embedCode}")
                }
            }
        }

        println("")
    }

    fun AbstractTest.dumpBlogs(
        data: Array<Blog>,
        sp: String = ""
    ) {
        for (blog in data) dump(blog, sp)
    }

    fun AbstractTest.dumpPosts(
        data: Array<Post>,
        sp: String = ""
    ) {
        for (post in data) dump(post, sp)
    }
}
