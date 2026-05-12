package work.socialhub.ktumblr.api

import work.socialhub.ktumblr.api.request.blog.BlogAvatarRequest
import work.socialhub.ktumblr.api.request.blog.BlogBannerRequest
import work.socialhub.ktumblr.api.request.blog.BlogDraftsRequest
import work.socialhub.ktumblr.api.request.blog.BlogFollowersRequest
import work.socialhub.ktumblr.api.request.blog.BlogFollowingRequest
import work.socialhub.ktumblr.api.request.blog.BlogInfoRequest
import work.socialhub.ktumblr.api.request.blog.BlogLikesRequest
import work.socialhub.ktumblr.api.request.blog.BlogPostsRequest
import work.socialhub.ktumblr.api.request.blog.BlogQueueRequest
import work.socialhub.ktumblr.api.request.blog.BlogSubmissionsRequest
import work.socialhub.ktumblr.api.request.blog.BlogUpdateInfoRequest
import work.socialhub.ktumblr.api.request.blog.post.BlogDeleteRequest
import work.socialhub.ktumblr.api.request.blog.post.BlogPostEditTagsRequest
import work.socialhub.ktumblr.api.request.blog.post.BlogPostRequest
import work.socialhub.ktumblr.api.request.blog.post.BlogPostUpdateRequest
import work.socialhub.ktumblr.api.request.blog.post.BlogReblogRequest
import work.socialhub.ktumblr.api.response.Body
import work.socialhub.ktumblr.api.response.Response
import work.socialhub.ktumblr.api.response.ResponseUnit
import work.socialhub.ktumblr.api.response.blog.BlogBannerResponse
import work.socialhub.ktumblr.api.response.blog.BlogFollowersResponse
import work.socialhub.ktumblr.api.response.blog.BlogFollowingResponse
import work.socialhub.ktumblr.api.response.blog.BlogInfoResponse
import work.socialhub.ktumblr.api.response.blog.BlogLikesResponse
import work.socialhub.ktumblr.api.response.blog.BlogPostsResponse
import kotlin.js.JsExport

@JsExport
interface BlogResource {

    /**
     * Get the blog info for a given blog
     */
    suspend fun blogInfo(
        request: BlogInfoRequest
    ): Response<Body<BlogInfoResponse>>

    @JsExport.Ignore
    fun blogInfoBlocking(
        request: BlogInfoRequest
    ): Response<Body<BlogInfoResponse>>

    /**
     * Get a specific size avatar for a given blog
     */
    suspend fun blogAvatar(
        request: BlogAvatarRequest
    ): Response<String>

    @JsExport.Ignore
    fun blogAvatarBlocking(
        request: BlogAvatarRequest
    ): Response<String>

    /**
     * Get the public likes for a given blog
     */
    suspend fun blogLikes(
        request: BlogLikesRequest
    ): Response<Body<BlogLikesResponse>>

    @JsExport.Ignore
    fun blogLikesBlocking(
        request: BlogLikesRequest
    ): Response<Body<BlogLikesResponse>>

    /**
     * Get the following for a given blog
     */
    suspend fun blogFollowing(
        request: BlogFollowingRequest
    ): Response<Body<BlogFollowingResponse>>

    @JsExport.Ignore
    fun blogFollowingBlocking(
        request: BlogFollowingRequest
    ): Response<Body<BlogFollowingResponse>>

    /**
     * Get the followers for a given blog
     */
    suspend fun blogFollowers(
        request: BlogFollowersRequest
    ): Response<Body<BlogFollowersResponse>>

    @JsExport.Ignore
    fun blogFollowersBlocking(
        request: BlogFollowersRequest
    ): Response<Body<BlogFollowersResponse>>

    /**
     * Get the posts for a given blog
     */
    suspend fun blogPosts(
        request: BlogPostsRequest
    ): Response<Body<BlogPostsResponse>>

    @JsExport.Ignore
    fun blogPostsBlocking(
        request: BlogPostsRequest
    ): Response<Body<BlogPostsResponse>>

    /**
     * Get the queued posts for a given blog
     */
    suspend fun blogQueuedPosts(
        request: BlogQueueRequest
    ): Response<Body<BlogPostsResponse>>

    @JsExport.Ignore
    fun blogQueuedPostsBlocking(
        request: BlogQueueRequest
    ): Response<Body<BlogPostsResponse>>

    /**
     * Get the draft posts for a given blog
     */
    suspend fun blogDraftPosts(
        request: BlogDraftsRequest
    ): Response<Body<BlogPostsResponse>>

    @JsExport.Ignore
    fun blogDraftPostsBlocking(
        request: BlogDraftsRequest
    ): Response<Body<BlogPostsResponse>>

    /**
     * Get the submissions for a given blog
     */
    suspend fun blogSubmissions(
        request: BlogSubmissionsRequest
    ): Response<Body<BlogPostsResponse>>

    @JsExport.Ignore
    fun blogSubmissionsBlocking(
        request: BlogSubmissionsRequest
    ): Response<Body<BlogPostsResponse>>

    /**
     * Create a post
     */
    suspend fun postCreate(
        request: BlogPostRequest
    ): ResponseUnit

    @JsExport.Ignore
    fun postCreateBlocking(
        request: BlogPostRequest
    ): ResponseUnit

    /**
     * Save edits for a given post
     */
    suspend fun postEdit(
        request: BlogPostRequest
    ): ResponseUnit

    @JsExport.Ignore
    fun postEditBlocking(
        request: BlogPostRequest
    ): ResponseUnit

    /**
     * Reblog a given post
     */
    suspend fun postReblog(
        request: BlogReblogRequest
    ): ResponseUnit

    @JsExport.Ignore
    fun postReblogBlocking(
        request: BlogReblogRequest
    ): ResponseUnit

    /**
     * Delete a given post
     */
    suspend fun postDelete(
        request: BlogDeleteRequest
    ): ResponseUnit

    @JsExport.Ignore
    fun postDeleteBlocking(
        request: BlogDeleteRequest
    ): ResponseUnit

    /**
     * Get the banner for a given blog
     */
    suspend fun blogBanner(
        request: BlogBannerRequest
    ): Response<Body<BlogBannerResponse>>

    @JsExport.Ignore
    fun blogBannerBlocking(
        request: BlogBannerRequest
    ): Response<Body<BlogBannerResponse>>

    /**
     * Update the blog info
     */
    suspend fun blogUpdateInfo(
        request: BlogUpdateInfoRequest
    ): ResponseUnit

    @JsExport.Ignore
    fun blogUpdateInfoBlocking(
        request: BlogUpdateInfoRequest
    ): ResponseUnit

    /**
     * Edit tags for a given post
     */
    suspend fun postEditTags(
        request: BlogPostEditTagsRequest
    ): ResponseUnit

    @JsExport.Ignore
    fun postEditTagsBlocking(
        request: BlogPostEditTagsRequest
    ): ResponseUnit

    /**
     * Update a post
     */
    suspend fun postUpdate(
        request: BlogPostUpdateRequest
    ): ResponseUnit

    @JsExport.Ignore
    fun postUpdateBlocking(
        request: BlogPostUpdateRequest
    ): ResponseUnit
}
