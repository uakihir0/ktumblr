package work.socialhub.ktumblr.internal

import work.socialhub.ktumblr.api.response.Response
import work.socialhub.ktumblr.api.response.ResponseUnit

open class AbstractResourceImpl {

    fun <T> get(
        path: String,
        params: Map<String, Any> = mapOf(),
    ): Response<T> {
        TODO()
    }

    fun <T> apiKeyGet(
        path: String,
        params: Map<String, Any> = mapOf(),
    ): Response<T> {
        TODO()
    }

    fun <T> oauthGet(
        path: String,
        params: Map<String, Any> = mapOf(),
    ): Response<T> {
        TODO()
    }

    fun oauthPostUnit(
        path: String,
        params: Map<String, Any> = mapOf(),
    ): ResponseUnit {
        TODO()
    }

    protected fun blogPath(
        blogName: String,
        extPath: String,
    ): String {
        return "/blog/" + blogUrl(blogName) + extPath
    }

    protected fun blogUrl(
        blogName: String,
    ): String {
        return if (blogName.contains(".")) blogName
        else "$blogName.tumblr.com"
    }
}