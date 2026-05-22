> [English](./README.md)

# ktumblr.js

本レポジトリは、[ktumblr] の npm レポジトリです。[ktumblr] は Kotlin Multiplatform を用いて作成された Tumblr API v2 クライアントライブラリです。
そのため、Web アプリケーション等でも使用していただくことができます。
また、このレポジトリは [ktumblr] の GitHub Actions によって自動コミットされています。issue や pull request は [ktumblr] にお願いします。

## 使用方法

### 追加方法

npm で管理している場合、以下のコマンドでアプリケーションに追加することができます。
本レポジトリにはバージョンは存在せず、[ktumblr] のバージョンと一致するブランチが存在します。
どのバージョンの [ktumblr] を使用するかは、本レポジトリのブランチを指定することで決定します。
[ブランチ一覧](https://github.com/uakihir0/ktumblr.js/branches) からバージョンに対応するブランチを確認してください。

```shell
npm add uakihir0/ktumblr.js
or
npm add uakihir0/ktumblr.js#{{BRANCH_NAME}}
```

### 基本的な使い方

TypeScript の型情報も含まれており、TypeScript での記述をオススメします。
詳しい使い方については、[ktumblr] の README も合わせて確認してください。

#### 認証

OAuth 2.0 の認証情報を使用してクライアントを作成する場合は以下のようにします。

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

#### ブログ情報の取得

```typescript
import BlogInfoRequest = ktumblr.work.socialhub.ktumblr.api.entity.blog.BlogInfoRequest;

const response = await tumblr.blog().blogInfo(
  new BlogInfoRequest().also((it) => {
    it.blogName = "staff";
  })
);

console.log(response.data.response?.blog);
```

#### ブログ投稿の取得

```typescript
import BlogPostsRequest = ktumblr.work.socialhub.ktumblr.api.entity.blog.BlogPostsRequest;

const response = await tumblr.blog().blogPosts(
  new BlogPostsRequest().also((it) => {
    it.blogName = "uakihiro";
  })
);

console.log(response.data.response?.posts);
```

#### テキスト投稿の作成

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

#### ダッシュボードの取得

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

## ライセンス

MIT License

## 作者

[Akihiro Urushihara](https://github.com/uakihir0)

[ktumblr]: https://github.com/uakihir0/ktumblr