package work.socialhub.ktumblr.internal

import work.socialhub.khttpclient.HttpRequest
import work.socialhub.ktumblr.TumblrAuth
import work.socialhub.ktumblr.TumblrEndpoint.API_URL
import work.socialhub.ktumblr.api.BlogResource
import work.socialhub.ktumblr.api.request.blog.BlogAvatarRequest
import work.socialhub.ktumblr.api.request.blog.BlogDraftsRequest
import work.socialhub.ktumblr.api.request.blog.BlogFollowersRequest
import work.socialhub.ktumblr.api.request.blog.BlogFollowingRequest
import work.socialhub.ktumblr.api.request.blog.BlogInfoRequest
import work.socialhub.ktumblr.api.request.blog.BlogLikesRequest
import work.socialhub.ktumblr.api.request.blog.BlogPostsRequest
import work.socialhub.ktumblr.api.request.blog.BlogQueueRequest
import work.socialhub.ktumblr.api.request.blog.BlogSubmissionsRequest
import work.socialhub.ktumblr.api.request.blog.post.BlogAudioPostRequest
import work.socialhub.ktumblr.api.request.blog.post.BlogChatPostRequest
import work.socialhub.ktumblr.api.request.blog.post.BlogDeleteRequest
import work.socialhub.ktumblr.api.request.blog.post.BlogLinkPostRequest
import work.socialhub.ktumblr.api.request.blog.post.BlogPhotoPostRequest
import work.socialhub.ktumblr.api.request.blog.post.BlogPostRequest
import work.socialhub.ktumblr.api.request.blog.post.BlogQuotePostRequest
import work.socialhub.ktumblr.api.request.blog.post.BlogReblogRequest
import work.socialhub.ktumblr.api.request.blog.post.BlogTextPostRequest
import work.socialhub.ktumblr.api.request.blog.post.BlogVideoPostRequest
import work.socialhub.ktumblr.api.response.Body
import work.socialhub.ktumblr.api.response.Response
import work.socialhub.ktumblr.api.response.ResponseUnit
import work.socialhub.ktumblr.api.response.blog.BlogFollowersResponse
import work.socialhub.ktumblr.api.response.blog.BlogFollowingResponse
import work.socialhub.ktumblr.api.response.blog.BlogInfoResponse
import work.socialhub.ktumblr.api.response.blog.BlogLikesResponse
import work.socialhub.ktumblr.api.response.blog.BlogPostsResponse
import work.socialhub.ktumblr.util.toBlocking

class BlogResourceImpl(
    auth: TumblrAuth
) : BlogResource,
    AbstractResourceImpl(auth) {

    override suspend fun blogInfo(
        request: BlogInfoRequest
    ): Response<Body<BlogInfoResponse>> {
        return apiKeyGet(
            blogPath(request.blogName!!, "/info"),
        )
    }

    override fun blogInfoBlocking(
        request: BlogInfoRequest
    ): Response<Body<BlogInfoResponse>> = toBlocking { blogInfo(request) }

    override suspend fun blogAvatar(
        request: BlogAvatarRequest
    ): Response<String> {
        val ext = if (request.size == null) "" else "/${request.size!!}"
        val path = blogPath(request.blogName!!, "/avatar$ext")

        val r = HttpRequest()
            .url("$API_URL$path")
            .followRedirect(false)
            .get()

        val url = checkNotNull(r.headers["location"])
        { "Location header is not found." }
        Response(url[0], url[0])
    }

    override fun blogAvatarBlocking(
        request: BlogAvatarRequest
    ): Response<String> = toBlocking { blogAvatar(request) }

    override suspend fun blogLikes(
        request: BlogLikesRequest
    ): Response<Body<BlogLikesResponse>> {
        return apiKeyGet(
            blogPath(request.blogName!!, "/likes"),
            request.toMap()
        )
    }

    override fun blogLikesBlocking(
        request: BlogLikesRequest
    ): Response<Body<BlogLikesResponse>> = toBlocking { blogLikes(request) }

    override suspend fun blogFollowing(
        request: BlogFollowingRequest
    ): Response<Body<BlogFollowingResponse>> {
        return oauthGet(
            blogPath(request.blogName!!, "/following"),
            request.toMap()
        )
    }

    override fun blogFollowingBlocking(
        request: BlogFollowingRequest
    ): Response<Body<BlogFollowingResponse>> = toBlocking { blogFollowing(request) }

    override suspend fun blogFollowers(
        request: BlogFollowersRequest
    ): Response<Body<BlogFollowersResponse>> {
        return oauthGet(
            blogPath(request.blogName!!, "/followers"),
            request.toMap()
        )
    }

    override fun blogFollowersBlocking(
        request: BlogFollowersRequest
    ): Response<Body<BlogFollowersResponse>> = toBlocking { blogFollowers(request) }

    override suspend fun blogPosts(
        request: BlogPostsRequest
    ): Response<Body<BlogPostsResponse>> {
        val ext = if (request.type == null) "" else "/${request.type!!}"
        return oauthGet(
            blogPath(request.blogName!!, "/posts$ext"),
            request.toMap()
        )
    }

    override fun blogPostsBlocking(
        request: BlogPostsRequest
    ): Response<Body<BlogPostsResponse>> = toBlocking { blogPosts(request) }

    override suspend fun blogQueuedPosts(
        request: BlogQueueRequest
    ): Response<Body<BlogPostsResponse>> {
        return oauthGet(
            blogPath(request.blogName!!, "/posts/queue"),
            request.toMap()
        )
    }

    override fun blogQueuedPostsBlocking(
        request: BlogQueueRequest
    ): Response<Body<BlogPostsResponse>> = toBlocking { blogQueuedPosts(request) }

    override suspend fun blogDraftPosts(
        request: BlogDraftsRequest
    ): Response<Body<BlogPostsResponse>> {
        return oauthGet(
            blogPath(request.blogName!!, "/posts/draft"),
            request.toMap()
        )
    }

    override fun blogDraftPostsBlocking(
        request: BlogDraftsRequest
    ): Response<Body<BlogPostsResponse>> = toBlocking { blogDraftPosts(request) }

    override suspend fun blogSubmissions(
        request: BlogSubmissionsRequest
    ): Response<Body<BlogPostsResponse>> {
        return oauthGet(
            blogPath(request.blogName!!, "/posts/submission"),
            request.toMap()
        )
    }

    override fun blogSubmissionsBlocking(
        request: BlogSubmissionsRequest
    ): Response<Body<BlogPostsResponse>> = toBlocking { blogSubmissions(request) }

    override suspend fun postCreate(
        request: BlogPostRequest
    ): ResponseUnit {
        return oauthPostUnit(
            blogPath(request.blogName!!, "/post"),
            when (request) {
                is BlogTextPostRequest -> request.toMap()
                is BlogPhotoPostRequest -> request.toMap()
                is BlogQuotePostRequest -> request.toMap()
                is BlogLinkPostRequest -> request.toMap()
                is BlogChatPostRequest -> request.toMap()
                is BlogAudioPostRequest -> request.toMap()
                is BlogVideoPostRequest -> request.toMap()
                else -> throw IllegalArgumentException("Unknown post type.")
            },
            when (request) {
                is BlogPhotoPostRequest -> request.toFileMap()
                else -> mapOf()
            }
        )
    }

    override fun postCreateBlocking(
        request: BlogPostRequest
    ): ResponseUnit = toBlocking { postCreate(request) }

    override suspend fun postEdit(
        request: BlogPostRequest
    ): ResponseUnit {
        return oauthPostUnit(
            blogPath(request.blogName!!, "/post/edit"),
            when (request) {
                is BlogTextPostRequest -> request.toMap()
                is BlogPhotoPostRequest -> request.toMap()
                is BlogQuotePostRequest -> request.toMap()
                is BlogLinkPostRequest -> request.toMap()
                is BlogChatPostRequest -> request.toMap()
                is BlogAudioPostRequest -> request.toMap()
                is BlogVideoPostRequest -> request.toMap()
                else -> throw IllegalArgumentException("Unknown post type.")
            }
        )
    }

    override fun postEditBlocking(
        request: BlogPostRequest
    ): ResponseUnit = toBlocking { postEdit(request) }

    override suspend fun postReblog(
        request: BlogReblogRequest
    ): ResponseUnit {
        return oauthPostUnit(
            blogPath(request.blogName!!, "/post/reblog"),
            request.toMap()
        )
    }

    override fun postReblogBlocking(
        request: BlogReblogRequest
    ): ResponseUnit = toBlocking { postReblog(request) }

    override suspend fun postDelete(
        request: BlogDeleteRequest
    ): ResponseUnit {
        return oauthPostUnit(
            blogPath(request.blogName!!, "/post/delete"),
            request.toMap()
        )
    }

    override fun postDeleteBlocking(
        request: BlogDeleteRequest
    ): ResponseUnit = toBlocking { postDelete(request) }
}
