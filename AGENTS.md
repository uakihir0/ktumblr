# Agent Documentation

## Overview

This repository is a Tumblr API client library for Kotlin Multiplatform. It provides REST API bindings for the Tumblr v2 API, including blog operations, user management, authentication (OAuth2), and tagged post retrieval.

## Key Concepts

### Tumblr API Structure

All Tumblr API calls are made to `https://api.tumblr.com/v2/{endpoint}` using GET or POST requests. Responses are JSON. Authentication is either via API key (query parameter `api_key`) or OAuth2 Bearer token (`Authorization: Bearer <token>` header).

Common endpoint categories:

- `blog/{blog-identifier}/info` - Blog information
- `blog/{blog-identifier}/posts` - Blog posts
- `blog/{blog-identifier}/likes` - Blog likes
- `blog/{blog-identifier}/followers` - Blog followers
- `blog/{blog-identifier}/following` - Blog following
- `blog/{blog-identifier}/avatar` - Blog avatar
- `blog/{blog-identifier}/post` - Create/edit/reblog posts
- `user/info` - Authenticated user info
- `user/dashboard` - User dashboard
- `user/likes` - User likes
- `user/following` - User following
- `user/follow` / `user/unfollow` - Follow/unfollow blogs
- `user/like` / `user/unlike` - Like/unlike posts
- `tagged` - Tagged posts

### API Specification Reference

Refer to the official Tumblr API documentation: https://www.tumblr.com/docs/en/api/v2

### Authentication

Tumblr uses OAuth2 for authentication:

1. Redirect users to the authorization URL with `client_id`, `redirect_uri`, `response_type`, and `scope`
2. After user approval, exchange the authorization code for an access token via the token endpoint
3. Use the access token in subsequent API requests as a Bearer token
4. Refresh tokens are supported via the token refresh endpoint

The `AuthResource` interface provides `authorizeUrl()`, `oAuth2Token()`, and `oAuth2TokenRefresh()` methods.

### Post Types (Legacy)

The library supports Tumblr's legacy post types:

- `LegacyTextPost` - Text/HTML content
- `LegacyPhotoPost` - Photo posts with multiple sizes
- `LegacyVideoPost` - Video content
- `LegacyAudioPost` - Audio content
- `LegacyLinkPost` - Link posts
- `LegacyQuotePost` - Quote posts
- `LegacyAnswerPost` - Ask/answer posts
- `LegacyChatPost` - Chat/dialogue posts

Post types are deserialized polymorphically via `PostSerializer`.

## Architecture

### Resource-based API Design

The library follows a resource-based architecture (matching kmisskey/kmastodon/kbsky/kslack patterns):

- **`Tumblr`** - Main `@JsExport` interface with resource accessors (`auth()`, `user()`, `blog()`, `tagged()`)
- **`TumblrFactory`** - `@JsExport` factory object with `instance(clientId, clientSecret, accessToken, refreshToken)` method
- **`TumblrImpl`** - Internal implementation that eagerly instantiates all resource implementations
- Each API category has its own **Resource interface** (e.g., `BlogResource`, `UserResource`)
- Each Resource interface has a corresponding **ResourceImpl** (e.g., `BlogResourceImpl`, `UserResourceImpl`) in the `internal/` package

### HTTP Client Abstraction

`AbstractResourceImpl` provides base HTTP methods:

- `get()` - Unauthenticated GET request
- `apiKeyGet()` - GET with API key query parameter
- `oauthGet()` - GET with OAuth2 Bearer token header
- `oauthPostUnit()` - POST with OAuth2, supports file uploads
- `proceed()` / `proceedUnit()` - Response parsing and error handling

Blog paths are resolved via `blogPath()` which handles both full domains (`example.tumblr.com`) and short names (`example`).

### JsExport Pattern

- Resource interfaces are annotated with `@JsExport`
- Suspend functions are exported to JS as Promise-based APIs via `-Xenable-suspend-function-exporting`

## Directory Structure

- **`core/`**: REST API client library
  - `Tumblr.kt` - Main `@JsExport` interface with resource accessors
  - `TumblrFactory.kt` - `@JsExport` factory object
  - `TumblrImpl.kt` - Internal implementation
  - `TumblrAuth.kt` - Authentication credentials holder
  - `TumblrEndpoint.kt` - API base URL constant
  - `TumblrException.kt` - Custom exception class
  - `api/` - Resource interfaces (`AuthResource`, `BlogResource`, `UserResource`, `TaggedResource`)
    - `request/` - Request objects organized by category
      - `auth/` - Auth requests (AuthAuthorizeUrlRequest, AuthOAuth2TokenRequest, etc.)
      - `blog/` - Blog requests (BlogInfoRequest, BlogPostsRequest, etc.)
      - `blog/post/` - Blog post mutation requests (BlogPostRequest, BlogDeleteRequest, BlogReblogRequest)
      - `user/` - User requests (UserDashboardRequest, UserFollowRequest, etc.)
      - `tagged/` - Tagged requests (TaggedRequest)
    - `response/` - Response objects organized by category
      - `auth/` - Auth responses
      - `blog/` - Blog responses
      - `user/` - User responses
      - `Response.kt` - Generic response wrapper
      - `ResponseUnit.kt` - Unit response (no body)
      - `Body.kt` - Response body wrapper
      - `Meta.kt` - Response metadata
  - `internal/` - Internal implementation
    - `AbstractResourceImpl.kt` - Base class for all resources
    - `AuthResourceImpl.kt`, `BlogResourceImpl.kt`, `UserResourceImpl.kt`, `TaggedResourceImpl.kt`
  - `entity/` - Data models
    - `post/` - Post entities (Post, Trail, and legacy post types)
    - `post/legacy/` - Legacy post type classes
    - `post/options/` - Post options (Photo, Video, PhotoSize, Dialogue)
    - `blog/` - Blog entities (Blog, BlogAvatar, BlogTheme, Reblog)
    - `user/` - User entities (User, FollowerUser)
    - `Note.kt`, `NoteAvatar.kt`, `Resource.kt`
  - `define/` - Enum definitions (PostType, PhotoType)
  - `util/` - Utilities (Json, MediaType)
    - `json/PostSerializer.kt` - Custom polymorphic post serializer
- **`all/`**: Aggregated module for platform distribution (JS, iOS, macOS frameworks)
- **`plugins/`**: Gradle build convention plugins
- **`tool/`**: Auxiliary tooling (rename_podfile.sh)
- **`docs/`**: Documentation (README translations, platform-specific docs)

## Testing

Run all core tests:

```shell
./gradlew :core:jvmTest
```

Run specific tests:

```shell
./gradlew :core:jvmTest --tests "work.socialhub.ktumblr.apis.BlogTest"
```

If network access is not available, verify successful build:

```shell
./gradlew jvmJar
```

Tests require Tumblr API credentials configured in `secrets.json` (see `secrets.json.default` for template).

## Implementation Guidelines

### API Endpoint Mapping

Tumblr API endpoints map to request/response classes organized by category:

- `blog/{blog}/info` → `request/blog/BlogInfoRequest` + `response/blog/BlogInfoResponse`
- `blog/{blog}/posts` → `request/blog/BlogPostsRequest` + `response/blog/BlogPostsResponse`
- `user/info` → (no request) + `response/user/UserResponse`
- `user/dashboard` → `request/user/UserDashboardRequest` + `response/user/UserDashboardResponse`

### Steps to Add a New API

1. Create the **request class** in `api/request/{category}/` (with properties mapped to API parameters)
2. Create the **response class** in `api/response/{category}/` (with `@Serializable` annotation)
3. Add the method signature to the **Resource interface** in `api/`
4. Implement the method in the **ResourceImpl** class in `internal/`
5. Add test coverage in `jvmTest/`

### Naming Conventions

| Type     | Naming Pattern            | Example                  |
| -------- | ------------------------- | ------------------------ |
| Request  | `{Action}Request`         | `BlogInfoRequest`        |
| Response | `{Action}Response`        | `BlogInfoResponse`       |
| Resource | `{Category}Resource`      | `BlogResource`           |
| Impl     | `{Category}ResourceImpl`  | `BlogResourceImpl`       |
| Entity   | Singular form             | `Post`, `Blog`, `User`   |

### Serialization

All response models use `kotlinx.serialization`:

- Annotate with `@Serializable`
- Use `@SerialName("json_field_name")` for field mapping
- Custom `PostSerializer` handles polymorphic post type deserialization based on the `type` field
- JSON configuration: `ignoreUnknownKeys = true`, `explicitNulls = false`

## Key File References

| Purpose              | File Path                                                                             |
| -------------------- | ------------------------------------------------------------------------------------- |
| Main interface       | `core/src/commonMain/kotlin/work/socialhub/ktumblr/Tumblr.kt`                        |
| Factory              | `core/src/commonMain/kotlin/work/socialhub/ktumblr/TumblrFactory.kt`                 |
| Implementation       | `core/src/commonMain/kotlin/work/socialhub/ktumblr/internal/TumblrImpl.kt`            |
| Auth credentials     | `core/src/commonMain/kotlin/work/socialhub/ktumblr/TumblrAuth.kt`                    |
| API endpoint         | `core/src/commonMain/kotlin/work/socialhub/ktumblr/TumblrEndpoint.kt`                |
| Abstract base class  | `core/src/commonMain/kotlin/work/socialhub/ktumblr/internal/AbstractResourceImpl.kt`  |
| Resource interfaces  | `core/src/commonMain/kotlin/work/socialhub/ktumblr/api/`                              |
| Resource impls       | `core/src/commonMain/kotlin/work/socialhub/ktumblr/internal/`                         |
| Request models       | `core/src/commonMain/kotlin/work/socialhub/ktumblr/api/request/`                      |
| Response models      | `core/src/commonMain/kotlin/work/socialhub/ktumblr/api/response/`                     |
| Entity models        | `core/src/commonMain/kotlin/work/socialhub/ktumblr/entity/`                           |
| Post serializer      | `core/src/commonMain/kotlin/work/socialhub/ktumblr/util/json/PostSerializer.kt`       |
| JSON utilities       | `core/src/commonMain/kotlin/work/socialhub/ktumblr/util/Json.kt`                      |
