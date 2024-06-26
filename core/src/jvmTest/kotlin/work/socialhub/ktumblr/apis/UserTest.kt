package work.socialhub.ktumblr.apis

import work.socialhub.ktumblr.AbstractTest
import work.socialhub.ktumblr.Printer.dump
import work.socialhub.ktumblr.Printer.dumpPosts
import work.socialhub.ktumblr.api.request.user.UserDashboardRequest
import kotlin.test.Test

class UserTest : AbstractTest() {

    @Test
    fun testUserMe() {
        val user = checkToken {
            tumblr().user().user()
        }
        println(user.json)
        dump(user.data.response?.user!!)
    }

    @Test
    fun testUserDashboard() {
        val user = checkToken {
            tumblr().user().userDashboard(
                UserDashboardRequest().also {
                    it.reblogInfo = true
                    it.notesInfo = true
                    it.limit = 10
                }
            )
        }
        println(user.json)
        dumpPosts(user.data.response?.posts!!)
    }
}