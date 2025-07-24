package com.dev.martyniuk.local.chat.ui.view.ext

import com.dev.martyniuk.local.chat.core.extensions.isNull
import com.google.android.material.textfield.TextInputEditText

fun TextInputEditText.updateText(value: String) {
    if (this.text.isNull()) setText(value)
    else this.text!!.replace(0, value.length, value)
}