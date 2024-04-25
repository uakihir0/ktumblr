package work.socialhub.ktumblr

import work.socialhub.ktumblr.entity.blog.Blog
import work.socialhub.ktumblr.entity.post.Post

object Printer {

    fun AbstractTest.dump(
        blog: Blog,
        sp: String = ""
    ) {
        println("${sp}=== Blog ===")
        println("${sp}Title       > ${blog.title}")
        println("${sp}Name        > ${blog.name}")
        println("${sp}Url         > ${blog.url}")
        println("")
    }


    fun AbstractTest.dump(
        post: Post,
        sp: String = ""
    ) {
        println("${sp}=== Post ===")
        println("${sp}Type  > ${post.type}")
        println("${sp}Title > ${post.title}")
        println("${sp}Body  > ${post.body}")
        println("${sp}Url   > ${post.postUrl}")
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
