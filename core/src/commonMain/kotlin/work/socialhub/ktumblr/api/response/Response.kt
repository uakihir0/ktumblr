package work.socialhub.ktumblr.api.response

data class Response<T>(
    var data: T,
    var json: String,
    val status: Int? = null,
    val message: String? = null,
)