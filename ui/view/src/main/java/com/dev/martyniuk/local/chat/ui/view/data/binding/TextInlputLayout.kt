package com.dev.martyniuk.local.chat.ui.view.data.binding

import androidx.databinding.BindingAdapter
import com.dev.martyniuk.local.chat.core.R
import com.google.android.material.textfield.TextInputLayout

private fun inputError(view: TextInputLayout, showError: Boolean, resId: Int) {
    view.error = if (showError) view.context.resources.getString(resId) else null
    view.isErrorEnabled = showError
}

@BindingAdapter("emailValidation")
fun emailInputError(view: TextInputLayout, showError: Boolean) =
    inputError(view, showError, R.string.sign_in_email_invalid)

@BindingAdapter("passwordValidation")
fun passwordInputError(view: TextInputLayout, showError: Boolean) =
    inputError(view, showError, R.string.sign_in_password_invalid)