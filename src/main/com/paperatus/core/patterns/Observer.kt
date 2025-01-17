package com.paperatus.core.patterns

/**
 * Allows a class to receive events without coupling code together.
 */
interface Observer {
    fun receive(what: Int, payload: Any?)
}
