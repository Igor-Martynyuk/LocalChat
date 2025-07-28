package com.dev.martyniuk.local.chat.ui.view.root.auth.signin

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.dev.martyniuk.local.chat.ui.view.databinding.FragmentAuthSignInBinding
import com.dev.martyniuk.local.chat.ui.view.root.auth.FragmentCredentials
import com.dev.martyniuk.local.chat.ui.view.view.SmartErrorTextInputLayout
import com.google.android.material.textfield.TextInputEditText
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FragmentSignIn : FragmentCredentials() {
    private lateinit var binding: FragmentAuthSignInBinding
    override val viewModel: ViewModelSignIn by viewModels()

    override val inputEmail: TextInputEditText get() = binding.inputEmail
    override val inputLayoutEmail: SmartErrorTextInputLayout get() = binding.inputEmailLayout
    override val inputPassword: TextInputEditText get() = binding.inputPassword
    override val inputLayoutPassword: SmartErrorTextInputLayout get() = binding.inputPasswordLayout

    override fun onCreateView(inflater: LayoutInflater, group: ViewGroup?, state: Bundle?) =
        FragmentAuthSignInBinding.inflate(inflater).also { binding = it }.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnRestoreAccount.setOnClickListener { viewModel.onRestoreAccountCommand() }
        binding.btnSignIn.setOnClickListener { viewModel.onSignInCommand() }
        binding.btnSignInGoogle.setOnClickListener { viewModel.onSignInWithGoogleCommand() }
        binding.btnSignInMicrosoft.setOnClickListener { viewModel.onSignInWithMicrosoft() }
        binding.btnSignInFacebook.setOnClickListener { viewModel.onSignInWithFacebook() }
        binding.btnSignUp.setOnClickListener { viewModel.onSignUpCommand() }

        viewModel.isSignInEnabled.observe(viewLifecycleOwner, binding.btnSignIn::setEnabled)
        binding.btnSignIn.setOnClickListener { viewModel.onSignInCommand() }
    }
}