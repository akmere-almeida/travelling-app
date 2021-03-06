package com.akmere.travelling_app.common
/**
 * Logging interface for [com.akmere.travelling_app] module.
 *
 * @see DebugLogger
 */
interface Logger {

    /**
     * The minimum level for this logger to log.
     *
     * @see [Log](https://developer.android.com/reference/android/util/Log.html)
     */
    var level: Int

    /**
     * Write [message] and/or [throwable] to a logging destination.
     *
     * [priority] will be greater than or equal to [level].
     */
    fun log(tag: String, priority: Int, message: String?, throwable: Throwable?)
}
