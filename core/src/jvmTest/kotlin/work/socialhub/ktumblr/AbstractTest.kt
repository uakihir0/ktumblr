package work.socialhub.ktumblr

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import work.socialhub.ktumblr.util.Json.fromJson
import java.io.FileReader
import kotlin.test.BeforeTest


open class AbstractTest {

    companion object {
        const val TEST_ACCOUNT_INDEX = 0

        var CLIENT_ID: String? = null
        var CLIENT_SECRET: String? = null
        var USER_TOKEN: String? = null
        var REFRESH_TOKEN: String? = null
    }

    fun tumblr(): Tumblr {
        return TumblrFactory.instance(
            CLIENT_ID!!,
            CLIENT_SECRET!!,
            USER_TOKEN,
            REFRESH_TOKEN,
        )
    }

    /**
     * Read File
     */
    private fun readFile(file: String): String {
        return FileReader(file).readText()
    }

    @BeforeTest
    fun setupTest() {
        try {
            // Get account handle and password.
            val json = readFile("../secrets.json")
            val props = fromJson<Secrets>(json)
            val param = props.params[TEST_ACCOUNT_INDEX]

            CLIENT_ID = param.clientId
            CLIENT_SECRET = param.clientSecret
            USER_TOKEN = param.userToken
            REFRESH_TOKEN = param.refreshToken

        } catch (e: Exception) {
            e.printStackTrace()
        }
    }


    @Serializable
    class Secrets {
        var params: List<SecretParams> = listOf()
    }

    @Serializable
    class SecretParams {
        var host: String? = null

        @SerialName("client_id")
        var clientId: String? = null

        @SerialName("client_secret")
        var clientSecret: String? = null

        @SerialName("user_token")
        var userToken: String? = null

        @SerialName("refresh_token")
        var refreshToken: String? = null
    }
}