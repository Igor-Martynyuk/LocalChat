package com.dev.martyniuk.local.chat.ui.view.root.auth.signup.photo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.dev.martyniuk.local.chat.ui.view.databinding.FragmentAuthSignUpPhotoBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FragmentSignUpPhoto : Fragment() {
    private lateinit var binding: FragmentAuthSignUpPhotoBinding
    private lateinit var onImageReceivedCommand: ActivityResultLauncher<String>
    private val viewModel: ViewModelSignUpPhoto by viewModels()

    override fun onCreateView(inflater: LayoutInflater, group: ViewGroup?, state: Bundle?): View =
        FragmentAuthSignUpPhotoBinding
            .inflate(inflater)
            .also {
                binding = it
                onImageReceivedCommand = registerForActivityResult(
                    ActivityResultContracts.GetContent(),
                    viewModel::onUriReceived
                )
            }
            .root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.bitmap.observe(viewLifecycleOwner, binding.imgCropView::setImageBitmap)

        binding.btnSelectPhoto.setOnClickListener { onImageReceivedCommand.launch("image/*") }
    }
}