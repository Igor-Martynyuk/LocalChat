package com.dev.martyniuk.local.chat.ui.view.ext

import androidx.lifecycle.LiveData
import androidx.lifecycle.asLiveData
import androidx.lifecycle.map
import kotlinx.coroutines.flow.Flow

class Event<out T>(private val content: T) {
    private var isHandled = false

    fun peek(): T? =
        if (isHandled) null
        else {
            isHandled = true
            content
        }
}

fun <T> Flow<T>.asLiveEventExt(): LiveData<Event<T>> = this
    .asLiveData()
    .map { Event(it) }