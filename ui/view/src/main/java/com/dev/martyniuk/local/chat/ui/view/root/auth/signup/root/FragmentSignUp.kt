package com.dev.martyniuk.local.chat.ui.view.root.auth.signup.root

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.viewModels
import androidx.lifecycle.map
import com.dev.martyniuk.local.chat.ui.view.databinding.FragmentAuthSignUpBinding
import com.dev.martyniuk.local.chat.ui.view.ext.updateText
import com.dev.martyniuk.local.chat.ui.view.root.auth.FragmentCredentials
import com.dev.martyniuk.local.chat.ui.view.view.SmartErrorTextInputLayout
import com.google.android.material.textfield.TextInputEditText
import dagger.hilt.android.AndroidEntryPoint
import com.dev.martyniuk.local.chat.core.R

@AndroidEntryPoint
class FragmentSignUp : FragmentCredentials() {
    private lateinit var binding: FragmentAuthSignUpBinding
    override val viewModel: ViewModelSignUp by viewModels()

    override val inputEmail: TextInputEditText get() = binding.inputEmail
    override val inputLayoutEmail: SmartErrorTextInputLayout get() = binding.layoutEmail
    override val inputPassword: TextInputEditText get() = binding.inputPassword
    override val inputLayoutPassword: SmartErrorTextInputLayout get() = binding.layoutPassword

    override fun onCreateView(inflater: LayoutInflater, group: ViewGroup?, state: Bundle?) =
        FragmentAuthSignUpBinding.inflate(inflater).also { binding = it }.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.inputDisplayName.doAfterTextChanged { viewModel.onInputDisplayName(it.toString()) }
        viewModel.displayName.observe(viewLifecycleOwner, binding.inputDisplayName::updateText)
        viewModel.isDisplayNameValid
            .map { if (it) null else R.string.auth_display_name_invalid }
            .observe(viewLifecycleOwner, binding.layoutDisplayName::setError)

        binding.inputPasswordConfirmation.doAfterTextChanged { viewModel.onInputPassConfirmation(it.toString()) }
        viewModel.confirmation.observe(
            viewLifecycleOwner,
            binding.inputPasswordConfirmation::updateText
        )
        viewModel.isConfirmationValid
            .map { if (it) null else R.string.auth_password_confirm_invalid }
            .observe(viewLifecycleOwner, binding.layoutPasswordConfirmation::setError)

        binding.btnSignIn.setOnClickListener { viewModel.onSignInCommand() }

        viewModel.isFormFilled.observe(viewLifecycleOwner, binding.btnNavigateNext::setEnabled)
        binding.btnNavigateNext.setOnClickListener { viewModel.onNextCommand() }
    }
}