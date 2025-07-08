package com.dev.martyniuk.local.chat.ui.view.data.binding

import android.view.View
import androidx.databinding.BindingAdapter

@BindingAdapter("onFocusedListener")
fun bindOnFocusListener(view: View, action: Runnable) {
    view.onFocusChangeListener = View.OnFocusChangeListener { _, isFocused ->
        if (isFocused) action.run()
    }
}