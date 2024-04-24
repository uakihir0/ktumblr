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
import work.socialhub.ktumblr.api.response.Response
import work.socialhub.ktumblr.api.response.ResponseUnit
import work.socialhub.ktumblr.entity.User
import work.socialhub.ktumblr.entity.blog.Blog
import work.socialhub.ktumblr.entity.post.Post

class UserResourceImpl(
    auth: TumblrAuth
) : UserResource,
    AbstractResourceImpl(auth) {

    override fun user(): Response<User> {
        return oauthGet("/user/info")
    }

    override fun userDashboard(
        request: UserDashboardRequest
    ): Response<Array<Post>> {
        return oauthGet(
            "/user/dashboard",
            request.toMap()
        )
    }

    override fun userFollowing(
        request: UserFollowingRequest
    ): Response<Array<Blog>> {
        return oauthGet(
            "/user/following",
            request.toMap()
        )
    }

    override fun userLikes(
        request: UserLikesRequest
    ): Response<List<Post>> {
        return oauthGet(
            "/user/likes",
            request.toMap()
        )
    }

    override fun like(
        request: UserLikeRequest
    ): ResponseUnit {
        return oauthPostUnit(
            "/user/like",
            request.toMap()
        )
    }

    override fun unlike(
        request: UserUnlikeRequest
    ): ResponseUnit {
        return oauthPostUnit(
            "/user/unlike",
            request.toMap()
        )
    }

    override fun follow(
        request: UserFollowRequest
    ): ResponseUnit {
        return oauthPostUnit(
            "/user/follow",
            request.toMap()
        )
    }

    override fun unfollow(
        request: UserUnfollowRequest
    ): ResponseUnit {
        return oauthPostUnit(
            "/user/unfollow",
            request.toMap()
        )
    }
}