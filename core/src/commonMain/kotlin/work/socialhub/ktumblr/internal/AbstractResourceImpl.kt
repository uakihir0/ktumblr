package work.socialhub.ktumblr.internal

import work.socialhub.khttpclient.HttpRequest
import work.socialhub.khttpclient.HttpResponse
import work.socialhub.kmpcommon.runBlocking
import work.socialhub.ktumblr.TumblrAuth
import work.socialhub.ktumblr.TumblrEndpoint.API_URL
import work.socialhub.ktumblr.TumblrException
import work.socialhub.ktumblr.api.response.Response
import work.socialhub.ktumblr.api.response.ResponseUnit
import work.socialhub.ktumblr.util.Json.fromJson
import work.socialhub.ktumblr.util.MediaType

open class AbstractResourceImpl(
    val auth: TumblrAuth
) {

    inline fun <reified T> get(
        path: String,
        params: Map<String, Any> = mapOf(),
    ): Response<T> {
        return runBlocking {
            proceed<T> {
                HttpRequest()
                    .url("$API_URL$path")
                    .accept(MediaType.JSON)
                    .queries(params)
                    .get()
            }
        }
    }

    inline fun <reified T> apiKeyGet(
        path: String,
        params: Map<String, Any> = mapOf(),
    ): Response<T> {
        return runBlocking {
            proceed<T> {
                HttpRequest()
                    .url("$API_URL$path")
                    .accept(MediaType.JSON)
                    .queries(params)
                    .query("api_key", auth.clientId)
                    .get()
            }
        }
    }

    inline fun <reified T> oauthGet(
        path: String,
        params: Map<String, Any> = mapOf(),
    ): Response<T> {
        return runBlocking {
            proceed<T> {
                HttpRequest()
                    .url("$API_URL$path")
                    .header("Authorization", auth.oAuthBearerToken())
                    .accept(MediaType.JSON)
                    .queries(params)
                    .get()
            }
        }
    }

    fun oauthPostUnit(
        path: String,
        params: Map<String, Any> = mapOf(),
    ): ResponseUnit {
        return runBlocking {
            proceedUnit {
                HttpRequest()
                    .url("$API_URL$path")
                    .header("Authorization", auth.oAuthBearerToken())
                    .accept(MediaType.JSON)
                    .params(params)
                    .post()
            }
        }
    }

    inline fun <reified T> proceed(
        body: () -> HttpResponse
    ): Response<T> {
        try {
            val response = body()
            if (response.status == 200) {
                return Response(
                    fromJson<T>(response.stringBody),
                    response.stringBody
                )
            }
            throw TumblrException(
                response.status,
                response.stringBody
            )
        } catch (e: Exception) {
            throw TumblrException(e)
        }
    }

    private inline fun proceedUnit(
        body: () -> HttpResponse
    ): ResponseUnit {
        try {
            val response = body()
            if (response.status == 200) {
                return ResponseUnit(
                    response.stringBody
                )
            }
            throw TumblrException(
                response.status,
                response.stringBody
            )
        } catch (e: Exception) {
            throw TumblrException(e)
        }
    }

    protected fun blogPath(
        blogName: String,
        extPath: String,
    ): String {
        return "/blog/" + blogUrl(blogName) + extPath
    }

    private fun blogUrl(
        blogName: String,
    ): String {
        return if (blogName.contains(".")) blogName
        else "$blogName.tumblr.com"
    }
}