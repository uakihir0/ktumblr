package work.socialhub.ktumblr.entity.post.legacy

import work.socialhub.ktumblr.entity.post.Post
import work.socialhub.ktumblr.entity.post.options.Photo

/**
 * This class represents a post of type "photo"
 */
class LegacyPhotoPost : Post() {

    var caption: String? = null
    var width: Int? = null
    var height: Int? = null
    var photos: Array<Photo>? = null
}
