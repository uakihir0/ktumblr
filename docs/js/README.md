> [日本語](./README_ja.md)

# ktumblr.js

This repository is the npm repository for [ktumblr]. [ktumblr] is a Tumblr API v2 client library created using Kotlin Multiplatform.
Therefore, it can be used in web applications and similar contexts.
Additionally, this repository is automatically committed by GitHub Actions of [ktumblr]. Please submit issues or pull requests to [ktumblr].

## Usage

### Installation

If you're managing with npm, you can add it to your application using the following command.
There are no versions in this repository, but there are branches corresponding to [ktumblr] versions.
Please check the branches on the [branch list](https://github.com/uakihir0/ktumblr.js/branches) to find the branch corresponding to the version you want to use.

```shell
npm add uakihir0/ktumblr.js
or
npm add uakihir0/ktumblr.js#{{BRANCH_NAME}}
```

### Basic Usage

TypeScript type information is included, so it's recommended to write in TypeScript.
Please also refer to the README of [ktumblr] for detailed usage.

#### Authentication

To create a client with OAuth 2.0 credentials, do as follows:

```typescript
import ktumblr from "ktumblr-js";
import TumblrFactory = ktumblr.work.socialhub.ktumblr.TumblrFactory;

const tumblr = TumblrFactory.instance(
  "YOUR_CLIENT_ID",
  "YOUR_CLIENT_SECRET",
  "ACCESS_TOKEN",
  "REFRESH_TOKEN"
);
```

#### Getting Blog Info

```typescript
import BlogInfoRequest = ktumblr.work.socialhub.ktumblr.api.entity.blog.BlogInfoRequest;

const response = await tumblr.blog().blogInfo(
  new BlogInfoRequest().also((it) => {
    it.blogName = "staff";
  })
);

console.log(response.data.response?.blog);
```

#### Getting Blog Posts

```typescript
import BlogPostsRequest = ktumblr.work.socialhub.ktumblr.api.entity.blog.BlogPostsRequest;

const response = await tumblr.blog().blogPosts(
  new BlogPostsRequest().also((it) => {
    it.blogName = "uakihiro";
  })
);

console.log(response.data.response?.posts);
```

#### Creating a Text Post

```typescript
import BlogTextPostRequest = ktumblr.work.socialhub.ktumblr.api.entity.blog.BlogTextPostRequest;
import PostType = ktumblr.work.socialhub.ktumblr.define.PostType;

await tumblr.blog().postCreate(
  new BlogTextPostRequest().also((it) => {
    it.blogName = "your-blog";
    it.state = "draft";
    it.type = PostType.TEXT.value;
    it.title = "Hello";
    it.body = "Hello World!";
  })
);
```

#### Getting Dashboard

```typescript
import UserDashboardRequest = ktumblr.work.socialhub.ktumblr.api.entity.user.UserDashboardRequest;

const response = await tumblr.user().userDashboard(
  new UserDashboardRequest().also((it) => {
    it.reblogInfo = true;
    it.notesInfo = true;
    it.limit = 10;
  })
);

console.log(response.data.response?.posts);
```

## License

MIT License

## Author

[Akihiro Urushihara](https://github.com/uakihir0)

[ktumblr]: https://github.com/uakihir0/ktumblr