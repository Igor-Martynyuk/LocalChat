package com.dev.martyniuk.local.chat.ui.view.ext

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import com.dev.martyniuk.local.chat.core.common.extensions.isNull

fun <T, V, R> LiveData<T>.combineLatestExt(
    that: LiveData<V>,
    action: (first: T, second: V) -> R
): LiveData<R> = MediatorLiveData<R>().apply {
    var t: T? = this@combineLatestExt.value
    var emittedT = t.isNull().not()

    var v: V? = that.value
    var emittedV = v.isNull().not()

    addSource(this@combineLatestExt) {
        t = it
        emittedT = true
        if (emittedV.not()) return@addSource
        value = action(t!!, v!!)
    }

    addSource(that) {
        v = it
        emittedV = true
        if (emittedT.not()) return@addSource
        value = action(t!!, v!!)
    }
}