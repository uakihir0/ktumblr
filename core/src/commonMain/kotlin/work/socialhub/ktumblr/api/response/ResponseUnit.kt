package work.socialhub.ktumblr.api.response

class ResponseUnit(
    var json: String,
    val status: Int? = null,
    val message: String? = null,
)