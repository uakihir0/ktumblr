package work.socialhub.ktumblr.internal

import work.socialhub.ktumblr.TumblrAuth
import work.socialhub.ktumblr.api.UserResource
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
import work.socialhub.ktumblr.util.toBlocking

class UserResourceImpl(
    auth: TumblrAuth
) : UserResource,
    AbstractResourceImpl(auth) {

    override suspend fun user(
    ): Response<Body<UserResponse>> {
        return oauthGet("/user/info")
    }

    override fun userBlocking(
    ): Response<Body<UserResponse>> = toBlocking { user() }

    override suspend fun userDashboard(
        request: UserDashboardRequest
    ): Response<Body<UserDashboardResponse>> {
        return oauthGet(
            "/user/dashboard",
            request.toMap()
        )
    }

    override fun userDashboardBlocking(
        request: UserDashboardRequest
    ): Response<Body<UserDashboardResponse>> = toBlocking { userDashboard(request) }

    override suspend fun userFollowing(
        request: UserFollowingRequest
    ): Response<Body<UserFollowingResponse>> {
        return oauthGet(
            "/user/following",
            request.toMap()
        )
    }

    override fun userFollowingBlocking(
        request: UserFollowingRequest
    ): Response<Body<UserFollowingResponse>> = toBlocking { userFollowing(request) }

    override suspend fun userLikes(
        request: UserLikesRequest
    ): Response<Body<UserLikesResponse>> {
        return oauthGet(
            "/user/likes",
            request.toMap()
        )
    }

    override fun userLikesBlocking(
        request: UserLikesRequest
    ): Response<Body<UserLikesResponse>> = toBlocking { userLikes(request) }

    override suspend fun like(
        request: UserLikeRequest
    ): ResponseUnit {
        return oauthPostUnit(
            "/user/like",
            request.toMap()
        )
    }

    override fun likeBlocking(
        request: UserLikeRequest
    ): ResponseUnit = toBlocking { like(request) }

    override suspend fun unlike(
        request: UserUnlikeRequest
    ): ResponseUnit {
        return oauthPostUnit(
            "/user/unlike",
            request.toMap()
        )
    }

    override fun unlikeBlocking(
        request: UserUnlikeRequest
    ): ResponseUnit = toBlocking { unlike(request) }

    override suspend fun follow(
        request: UserFollowRequest
    ): ResponseUnit {
        return oauthPostUnit(
            "/user/follow",
            request.toMap()
        )
    }

    override fun followBlocking(
        request: UserFollowRequest
    ): ResponseUnit = toBlocking { follow(request) }

    override suspend fun unfollow(
        request: UserUnfollowRequest
    ): ResponseUnit {
        return oauthPostUnit(
            "/user/unfollow",
            request.toMap()
        )
    }

    override fun unfollowBlocking(
        request: UserUnfollowRequest
    ): ResponseUnit = toBlocking { unfollow(request) }
}
