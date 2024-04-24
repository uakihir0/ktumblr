package work.socialhub.ktumblr.api

import work.socialhub.ktumblr.entity.User
import work.socialhub.ktumblr.api.request.user.UserDashboardRequest
import work.socialhub.ktumblr.api.request.user.UserFollowRequest
import work.socialhub.ktumblr.api.request.user.UserFollowingRequest
import work.socialhub.ktumblr.api.request.user.UserLikeRequest
import work.socialhub.ktumblr.api.request.user.UserLikesRequest
import work.socialhub.ktumblr.api.request.user.UserUnfollowRequest
import work.socialhub.ktumblr.api.request.user.UserUnlikeRequest
import work.socialhub.ktumblr.api.response.Response
import work.socialhub.ktumblr.api.response.ResponseUnit
import work.socialhub.ktumblr.entity.blog.Blog
import work.socialhub.ktumblr.entity.post.Post

interface UserResource {

    /**
     * Get the user info for the authenticated User
     */
    fun user(): Response<User>

    /**
     * Get the user dashboard for the authenticated User
     */
    fun userDashboard(
        request: UserDashboardRequest
    ): Response<Array<Post>>

    /**
     * Get the blogs the given user is following
     */
    fun userFollowing(
        request: UserFollowingRequest
    ): Response<Array<Blog>>

    /**
     * Get the likes for the authenticated user
     */
    fun userLikes(
        request: UserLikesRequest
    ): Response<List<Post>>

    /**
     * Like a given post
     */
    fun like(
        request: UserLikeRequest
    ): ResponseUnit

    /**
     * Unlike a given post
     */
    fun unlike(
        request: UserUnlikeRequest
    ): ResponseUnit

    /**
     * Follow a given blog
     */
    fun follow(
        request: UserFollowRequest
    ): ResponseUnit

    /**
     * Unfollow a given blog
     */
    fun unfollow(
        request: UserUnfollowRequest
    ): ResponseUnit
}