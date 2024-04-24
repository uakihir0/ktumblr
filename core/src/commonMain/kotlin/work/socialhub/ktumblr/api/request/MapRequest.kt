package work.socialhub.ktumblr.api.request

interface MapRequest {
    fun toMap(): Map<String, Any>

    fun MutableMap<String, Any>.addParam(
        key: String, value: Any?
    ) {
        if (value == null) return
        this[key] = value
    }
}

fun MutableMap<String, Any>.addParam(
    key: String, value: Any?
) {
    if (value == null) return
    this[key] = value
}