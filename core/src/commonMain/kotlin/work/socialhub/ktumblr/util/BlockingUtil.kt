package work.socialhub.ktumblr.util

import kotlinx.coroutines.CoroutineScope

expect fun <T> toBlocking(block: suspend CoroutineScope.() -> T): T
