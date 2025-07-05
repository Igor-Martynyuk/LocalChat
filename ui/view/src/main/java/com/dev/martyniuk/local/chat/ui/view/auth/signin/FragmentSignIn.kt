package com.dev.martyniuk.local.chat.ui.view.auth.signin

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.dev.martyniuk.local.chat.ui.view.databinding.FragmentAuthSignInBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FragmentSignIn : Fragment() {
    private val viewModel: ViewModelSignIn by viewModels()

    override fun onCreateView(inflater: LayoutInflater, group: ViewGroup?, state: Bundle?): View? =
        FragmentAuthSignInBinding.inflate(inflater)
            .also { it.viewModel = this.viewModel }
            .root
}