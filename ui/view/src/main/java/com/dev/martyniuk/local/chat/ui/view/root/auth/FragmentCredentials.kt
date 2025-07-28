package com.dev.martyniuk.local.chat.ui.view.root.auth

import android.os.Bundle
import android.view.View
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.Fragment
import androidx.lifecycle.map
import com.dev.martyniuk.local.chat.core.R
import com.dev.martyniuk.local.chat.ui.view.ext.updateText
import com.dev.martyniuk.local.chat.ui.view.view.SmartErrorTextInputLayout
import com.google.android.material.textfield.TextInputEditText

abstract class FragmentCredentials : Fragment() {
    abstract val viewModel: ViewModelCredentials

    abstract val inputEmail: TextInputEditText
    abstract val inputLayoutEmail: SmartErrorTextInputLayout
    abstract val inputPassword: TextInputEditText
    abstract val inputLayoutPassword: SmartErrorTextInputLayout

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        inputEmail.doAfterTextChanged { viewModel.onInputEmail(it.toString()) }
        viewModel.email.observe(viewLifecycleOwner, inputEmail::updateText)
        viewModel.isEmailValid
            .map { if (it) null else R.string.auth_email_invalid }
            .observe(viewLifecycleOwner, inputLayoutEmail::setError)

        inputPassword.doAfterTextChanged { viewModel.onInputPassword(it.toString()) }
        viewModel.pass.observe(viewLifecycleOwner, inputPassword::updateText)
        viewModel.isPassValid
            .map { if (it) null else R.string.auth_password_invalid }
            .observe(viewLifecycleOwner, inputLayoutPassword::setError)

    }
}