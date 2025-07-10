package com.dev.martyniuk.local.chat.ui.view.ext

import androidx.lifecycle.LiveData
import androidx.lifecycle.asLiveData
import androidx.lifecycle.map
import kotlinx.coroutines.flow.Flow

class Event<out T>(private val data: T) {
    private var isHandled = false

    fun consume(block: (T) -> Unit) {
        if (isHandled.not()){
            isHandled = true
            block(data)
        }
    }
}

fun <T> Flow<T>.asLiveEventExt(): LiveData<Event<T>> = this
    .asLiveData()
    .map { Event(it) }