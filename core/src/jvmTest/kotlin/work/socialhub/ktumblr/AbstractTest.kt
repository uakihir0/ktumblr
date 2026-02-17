package work.socialhub.ktumblr

import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import kotlinx.serialization.modules.SerializersModule
import work.socialhub.kmpcommon.AnySerializer
import work.socialhub.ktumblr.api.request.auth.AuthOAuth2TokenRefreshRequest
import work.socialhub.ktumblr.api.response.Response
import java.io.FileReader
import java.io.FileWriter
import kotlin.test.BeforeTest


open class AbstractTest {

    companion object {
        var CLIENT_ID: String? = null
        var CLIENT_SECRET: String? = null
        var ACCESS_TOKEN: String? = null
        var REFRESH_TOKEN: String? = null
    }

    fun tumblr(): Tumblr {
        return TumblrFactory.instance(
            CLIENT_ID!!,
            CLIENT_SECRET!!,
            ACCESS_TOKEN,
            REFRESH_TOKEN,
        )
    }

    inline fun <T> checkToken(
        func: () -> T
    ): T {
        try {
            return func()
        } catch (e: TumblrException) {
            println(">> TumblrException <<")
            println(">> ${e.status} <<")

            if (e.status == 401) {
                println(">> Refresh Token <<")
                val refresh = tumblr().auth().oAuth2TokenRefresh(
                    AuthOAuth2TokenRefreshRequest().also {
                        it.clientId = CLIENT_ID
                        it.clientSecret = CLIENT_SECRET
                        it.refreshToken = REFRESH_TOKEN
                    }
                )
                saveTokens(
                    refresh.data.accessToken!!,
                    refresh.data.refreshToken!!,
                )
                return func()
            }
            throw e
        }
    }

    private fun readFile(file: String): String {
        return FileReader(file).readText()
    }

    private fun writeFile(file: String, text: String) {
        val writer = FileWriter(file)
        writer.write(text)
        writer.close()
    }

    @BeforeTest
    fun setupTest() {
        try {
            val json = readFile("../secrets.json")
            val props = fromJson<Secrets>(json)
            val param = props.tumblr

            CLIENT_ID = param.clientId
            CLIENT_SECRET = param.clientSecret
            ACCESS_TOKEN = param.accessToken
            REFRESH_TOKEN = param.refreshToken

        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    fun saveTokens(
        accessToken: String,
        refreshToken: String,
    ) {
        ACCESS_TOKEN = accessToken
        REFRESH_TOKEN = refreshToken

        val param = Secrets().also { s ->
            s.tumblr = TumblrSecrets().also {
                it.clientId = CLIENT_ID
                it.clientSecret = CLIENT_SECRET
                it.accessToken = accessToken
                it.refreshToken = refreshToken
            }
        }
        writeFile("../secrets.json", toJson(param))
    }

    @Serializable
    class Secrets {
        var tumblr: TumblrSecrets = TumblrSecrets()
    }

    @Serializable
    class TumblrSecrets {
        @SerialName("TUMBLR_REDIRECT_URI")
        var redirectUri: String? = null
        @SerialName("TUMBLR_CLIENT_ID")
        var clientId: String? = null
        @SerialName("TUMBLR_CLIENT_SECRET")
        var clientSecret: String? = null
        @SerialName("TUMBLR_ACCESS_TOKEN")
        var accessToken: String? = null
        @SerialName("TUMBLR_REFRESH_TOKEN")
        var refreshToken: String? = null
    }

    @OptIn(ExperimentalSerializationApi::class)
    val json = Json {
        prettyPrint = true
        explicitNulls = false
        encodeDefaults = true
        ignoreUnknownKeys = true
        serializersModule = SerializersModule {
            contextual(Any::class, AnySerializer)
        }
    }

    private inline fun <reified T> toJson(obj: T): String {
        return json.encodeToString(obj)
    }

    private inline fun <reified T> fromJson(obj: String): T {
        return json.decodeFromString(obj)
    }
}