package com.dev.martyniuk.local.chat.ui.view.root.auth.signup.photo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.dev.martyniuk.local.chat.ui.view.databinding.FragmentAuthSingnUpPhotoBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FragmentSignUpPhoto : Fragment() {
    private val viewModel: ViewModelSignUpPhoto by viewModels()
    private val getPhotoCommand = registerForActivityResult(ActivityResultContracts.GetContent()) {
        it?.let { uri -> viewModel.onUriReceived(uri) }
    }

    override fun onCreateView(inflater: LayoutInflater, group: ViewGroup?, state: Bundle?): View =
        FragmentAuthSingnUpPhotoBinding
            .inflate(inflater)
            .also {
                it.lifecycleOwner = this
                it.contract = viewModel
                it.selectPhotoClickHandler = Runnable { getPhotoCommand.launch("image/*") }
            }
            .root
}