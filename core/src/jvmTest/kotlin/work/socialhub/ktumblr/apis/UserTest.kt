package work.socialhub.ktumblr.apis

import work.socialhub.ktumblr.AbstractTest
import work.socialhub.ktumblr.Printer.dump
import work.socialhub.ktumblr.Printer.dumpPosts
import work.socialhub.ktumblr.api.request.user.UserDashboardRequest
import kotlin.test.Test

class UserTest: AbstractTest() {

    @Test
    fun testUserMe() {
        val user = tumblr().user().user()
        dump(user.data.response?.user!!)
    }

    @Test
    fun testUserDashboard() {
        val user = tumblr().user().userDashboard(
            UserDashboardRequest().also {
                it.limit = 10
            }
        )
        println(user.json)
        dumpPosts(user.data.response?.posts!!)
    }
}