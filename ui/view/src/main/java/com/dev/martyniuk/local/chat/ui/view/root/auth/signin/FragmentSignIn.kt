package com.dev.martyniuk.local.chat.ui.view.root.auth.signin

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.OnFocusChangeListener
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.dev.martyniuk.local.chat.ui.view.R
import com.dev.martyniuk.local.chat.ui.view.databinding.FragmentAuthSignInBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FragmentSignIn : Fragment() {
    private val viewModel: ViewModelSignIn by viewModels()
    private lateinit var binding: FragmentAuthSignInBinding

    override fun onCreateView(inflater: LayoutInflater, group: ViewGroup?, state: Bundle?): View =
        FragmentAuthSignInBinding.inflate(inflater)
            .also {
                it.lifecycleOwner = this
                it.contract = this.viewModel
                binding = it
            }
            .root

    override fun onResume() {
        super.onResume()

        OnFocusChangeListener { view, isFocused ->
            when (view.id) {
                R.id.input_email -> if (isFocused) viewModel.enableEmailValidation()
                R.id.input_password -> if (isFocused) viewModel.enablePasswordValidation()
            }
        }.let {
            binding.inputEmail.onFocusChangeListener = it
            binding.inputPassword.onFocusChangeListener = it
        }

    }
}