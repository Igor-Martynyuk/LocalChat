package com.dev.martyniuk.local.chat.ui.view.root.auth.signup.credentials

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.dev.martyniuk.local.chat.ui.view.databinding.FragmentAuthSignUpCredentialsBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FragmentSignUpCredentials : Fragment() {
    private val viewModel: ViewModelSignUpCredentials by viewModels()

    override fun onCreateView(inflater: LayoutInflater, group: ViewGroup?, state: Bundle?): View =
        FragmentAuthSignUpCredentialsBinding.inflate(inflater)
            .also {
                it.lifecycleOwner = this
                it.contract = viewModel
            }
            .root
}