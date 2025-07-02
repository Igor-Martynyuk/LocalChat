package com.dev.martyniuk.local.chat.ui.view.auth.signin.microsoft

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.dev.martyniuk.local.chat.ui.view.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FragmentSignInMicrosoft : Fragment() {
    private val viewModel: ViewModelSignInMicrosoft by viewModels()

    override fun onCreateView(inflater: LayoutInflater, group: ViewGroup?, state: Bundle?): View? =
        inflater.inflate(R.layout.fragment_sign_in_microsoft, group, false)

}