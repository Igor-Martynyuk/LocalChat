package com.dev.martyniuk.local.chat.ui.view.root.auth.signup.credentials

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.dev.martyniuk.local.chat.ui.view.databinding.FragmentAuthSignUpBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FragmentSignUp : Fragment() {
    private val viewModel: ViewModelSignUp by viewModels()

    override fun onCreateView(inflater: LayoutInflater, group: ViewGroup?, state: Bundle?): View =
        FragmentAuthSignUpBinding.inflate(inflater)
            .also {
                it.lifecycleOwner = this
                it.contract = viewModel
                it.onEmailFocused = Runnable { viewModel.enableEmailValidation() }
                it.onPasswordFocused = Runnable { viewModel.enablePasswordValidation() }
                it.onConfirmationFocused = Runnable { viewModel.enableConfirmValidation() }
            }
            .root

}