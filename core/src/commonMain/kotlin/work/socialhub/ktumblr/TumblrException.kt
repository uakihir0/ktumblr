package work.socialhub.ktumblr

class TumblrException : RuntimeException {
    var status: Int? = null
    var body: String? = null

    constructor(m: String) : super(m)
    constructor(e: Exception) : super(e)

    constructor(
        status: Int,
        body: String
    ) : super("status code: $status, body: $body") {
        this.status = status
        this.body = body
    }
}
