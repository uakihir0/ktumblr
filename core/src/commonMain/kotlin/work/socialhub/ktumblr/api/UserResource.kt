package work.socialhub.ktumblr.api

import work.socialhub.ktumblr.api.request.user.UserDashboardRequest
import work.socialhub.ktumblr.api.request.user.UserFollowRequest
import work.socialhub.ktumblr.api.request.user.UserFollowingRequest
import work.socialhub.ktumblr.api.request.user.UserLikeRequest
import work.socialhub.ktumblr.api.request.user.UserLikesRequest
import work.socialhub.ktumblr.api.request.user.UserUnfollowRequest
import work.socialhub.ktumblr.api.request.user.UserUnlikeRequest
import work.socialhub.ktumblr.api.response.Body
import work.socialhub.ktumblr.api.response.Response
import work.socialhub.ktumblr.api.response.ResponseUnit
import work.socialhub.ktumblr.api.response.user.UserDashboardResponse
import work.socialhub.ktumblr.api.response.user.UserFollowingResponse
import work.socialhub.ktumblr.api.response.user.UserLikesResponse
import work.socialhub.ktumblr.api.response.user.UserResponse
import kotlin.js.JsExport

@JsExport
interface UserResource {

    /**
     * Get the user info for the authenticated User
     */
    suspend fun user(
    ): Response<Body<UserResponse>>

    @JsExport.Ignore
    fun userBlocking(
    ): Response<Body<UserResponse>>

    /**
     * Get the user dashboard for the authenticated User
     */
    suspend fun userDashboard(
        request: UserDashboardRequest
    ): Response<Body<UserDashboardResponse>>

    @JsExport.Ignore
    fun userDashboardBlocking(
        request: UserDashboardRequest
    ): Response<Body<UserDashboardResponse>>

    /**
     * Get the blogs the given user is following
     */
    suspend fun userFollowing(
        request: UserFollowingRequest
    ): Response<Body<UserFollowingResponse>>

    @JsExport.Ignore
    fun userFollowingBlocking(
        request: UserFollowingRequest
    ): Response<Body<UserFollowingResponse>>

    /**
     * Get the likes for the authenticated user
     */
    suspend fun userLikes(
        request: UserLikesRequest
    ): Response<Body<UserLikesResponse>>

    @JsExport.Ignore
    fun userLikesBlocking(
        request: UserLikesRequest
    ): Response<Body<UserLikesResponse>>

    /**
     * Like a given post
     */
    suspend fun like(
        request: UserLikeRequest
    ): ResponseUnit

    @JsExport.Ignore
    fun likeBlocking(
        request: UserLikeRequest
    ): ResponseUnit

    /**
     * Unlike a given post
     */
    suspend fun unlike(
        request: UserUnlikeRequest
    ): ResponseUnit

    @JsExport.Ignore
    fun unlikeBlocking(
        request: UserUnlikeRequest
    ): ResponseUnit

    /**
     * Follow a given blog
     */
    suspend fun follow(
        request: UserFollowRequest
    ): ResponseUnit

    @JsExport.Ignore
    fun followBlocking(
        request: UserFollowRequest
    ): ResponseUnit

    /**
     * Unfollow a given blog
     */
    suspend fun unfollow(
        request: UserUnfollowRequest
    ): ResponseUnit

    @JsExport.Ignore
    fun unfollowBlocking(
        request: UserUnfollowRequest
    ): ResponseUnit
}
