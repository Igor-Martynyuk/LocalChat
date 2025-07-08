package com.dev.martyniuk.local.chat.ui.view.ext

import androidx.lifecycle.LiveData
import androidx.lifecycle.asFlow
import androidx.lifecycle.asLiveData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.combine

fun <T, V, R> LiveData<T>.combineExt(
    that: LiveData<V>,
    scope: CoroutineScope,
    transform: (T, V) -> R
): LiveData<R> = this.asFlow()
    .combine(that.asFlow(), transform)
    .asLiveData(scope.coroutineContext)