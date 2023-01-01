package com.raminabbasiiii.movies.util

import androidx.lifecycle.Observer


/**
 * A lifecycle-aware observable that sends only new updates after
 * subscription, used for events like navigation. This avoids a
 * common problem with events like on configuration change.
 *
 * An [Observer] for [SingleEvent]s, simplifying the pattern of
 * checking if the [SingleEvent]'s content has already been handled.
 *
 * [onEventUnhandledContent] is *only* called if the [SingleEvent]'s
 * contents has not been handled.
 */
open class SingleEvent<T>(private val content: T) {
    var hasBeenHandled = false
        private set // Allow external read but not write

    /**
     * Returns the content and prevents its use again.
     */
    fun getContentIfNotHandled(): T? {
        return if (hasBeenHandled) {
            null
        } else {
            hasBeenHandled = true
            content
        }
    }

    /**
     * Returns the content, even if it's already been handled.
     */
    fun peekContent(): T = content
}

class EventObserver<T>(private val onEventUnhandledContent: (T) -> Unit) :
    Observer<SingleEvent<T>> {
    override fun onChanged(event: SingleEvent<T>?) {
        event?.getContentIfNotHandled()?.let {
            onEventUnhandledContent(it)
        }
    }
}