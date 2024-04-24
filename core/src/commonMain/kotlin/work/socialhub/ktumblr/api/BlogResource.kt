package work.socialhub.ktumblr.api

import work.socialhub.ktumblr.api.request.blog.BlogAvatarRequest
import work.socialhub.ktumblr.api.request.blog.BlogDraftsRequest
import work.socialhub.ktumblr.api.request.blog.BlogFollowersRequest
import work.socialhub.ktumblr.api.request.blog.BlogFollowingRequest
import work.socialhub.ktumblr.api.request.blog.BlogInfoRequest
import work.socialhub.ktumblr.api.request.blog.BlogLikesRequest
import work.socialhub.ktumblr.api.request.blog.BlogPostsRequest
import work.socialhub.ktumblr.api.request.blog.BlogQueueRequest
import work.socialhub.ktumblr.api.request.blog.BlogSubmissionsRequest
import work.socialhub.ktumblr.api.request.blog.post.BlogDeleteRequest
import work.socialhub.ktumblr.api.request.blog.post.BlogPostRequest
import work.socialhub.ktumblr.api.request.blog.post.BlogReblogRequest
import work.socialhub.ktumblr.api.response.Response
import work.socialhub.ktumblr.api.response.ResponseUnit
import work.socialhub.ktumblr.entity.blog.Blog
import work.socialhub.ktumblr.entity.post.Post

interface BlogResource {

    /**
     * Get the blog info for a given blog
     */
    fun blogInfo(
        request: BlogInfoRequest
    ): Response<Blog>

    /**
     * Get a specific size avatar for a given blog
     */
    fun blogAvatar(
        request: BlogAvatarRequest
    ): Response<String>

    /**
     * Get the public likes for a given blog
     */
    fun blogLikes(
        request: BlogLikesRequest
    ): Response<Array<Post>>

    /**
     * Get the following for a given blog
     */
    fun blogFollowing(
        request: BlogFollowingRequest
    ): Response<Array<Blog>>

    /**
     * Get the followers for a given blog
     */
    fun blogFollowers(
        request: BlogFollowersRequest
    ): Response<Array<Blog>>

    /**
     * Get the posts for a given blog
     */
    fun blogPosts(
        request: BlogPostsRequest
    ): Response<Array<Post>>

    /**
     * Get the queued posts for a given blog
     */
    fun blogQueuedPosts(
        request: BlogQueueRequest
    ): Response<Array<Post>>

    /**
     * Get the draft posts for a given blog
     */
    fun blogDraftPosts(
        request: BlogDraftsRequest
    ): Response<Array<Post>>

    /**
     * Get the submissions for a given blog
     */
    fun blogSubmissions(
        request: BlogSubmissionsRequest
    ): Response<Array<Post>>

    /**
     * Create a post
     */
    fun postCreate(
        request: BlogPostRequest
    ): ResponseUnit

    /**
     * Save edits for a given post
     */
    fun postEdit(
        request: BlogPostRequest
    ): ResponseUnit

    /**
     * Reblog a given post
     */
    fun postReblog(
        request: BlogReblogRequest
    ): ResponseUnit

    /**
     * Delete a given post
     */
    fun postDelete(
        request: BlogDeleteRequest
    ): ResponseUnit
}