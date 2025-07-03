package com.dev.martyniuk.local.chat.ui.view.auth.signin

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.dev.martyniuk.local.chat.ui.view.databinding.FragmentAuthSignInBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FragmentSignIn : Fragment() {
    private val viewModel: ViewModelSignIn by viewModels()
    private lateinit var binding: FragmentAuthSignInBinding

    override fun onCreateView(inflater: LayoutInflater, group: ViewGroup?, state: Bundle?): View? {
        binding = FragmentAuthSignInBinding.inflate(inflater)
        binding.viewModel = this.viewModel
        return binding.root
    }

    override fun onResume() {
        super.onResume()

        binding.inputEmail.editText?.doAfterTextChanged {
            viewModel.onEmailInput(it?.toString() ?: "")
        }

        binding.inputPassword.editText?.doAfterTextChanged {
            viewModel.onPasswordInput(it?.toString() ?: "")
        }
    }
}