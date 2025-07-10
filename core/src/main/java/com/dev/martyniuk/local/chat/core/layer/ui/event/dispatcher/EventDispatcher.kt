package com.dev.martyniuk.local.chat.core.layer.ui.event.dispatcher

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.launch

abstract class UiEventDispatcher<A> {
    private val scope = CoroutineScope(Dispatchers.Main.immediate)
    private val _flow = MutableSharedFlow<A>(extraBufferCapacity = 1, replay = 0)

    val flow: Flow<A> = _flow
    fun send(event: A) = scope.launch { _flow.emit(event) }
}