package com.dev.martyniuk.local.chat.ui.view.ext

import androidx.lifecycle.LiveData
import androidx.lifecycle.asFlow
import androidx.lifecycle.asLiveData
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.onEach
import kotlin.coroutines.CoroutineContext

fun <T, V, R> LiveData<T>.combine(
    that: LiveData<V>,
    context: CoroutineContext,
    transform: (T, V) -> R
): LiveData<R> = this.asFlow()
    .combine(that.asFlow(), transform)
    .asLiveData(context)

fun <T> LiveData<T>.onEach(action: (value: T) -> Unit) = this.asFlow()
    .onEach { action.invoke(it) }
    .asLiveData()