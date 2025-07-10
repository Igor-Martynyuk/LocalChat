package com.dev.martyniuk.local.chat.ui.view.root.auth.signup.photo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.dev.martyniuk.local.chat.ui.view.databinding.FragmentAuthSingnUpPhotoBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FragmentSignUpPhoto : Fragment() {
    private val viewModel: ViewModelSignUpPhoto by viewModels()
    private lateinit var binding: FragmentAuthSingnUpPhotoBinding

    override fun onCreateView(inflater: LayoutInflater, group: ViewGroup?, state: Bundle?): View =
        FragmentAuthSingnUpPhotoBinding
            .inflate(inflater)
            .also {
                it.lifecycleOwner = this
                it.contract = viewModel

                binding = it
            }
            .root
}