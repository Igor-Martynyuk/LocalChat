package com.dev.martyniuk.local.chat.ui.view.root.auth.signup.photo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.map
import com.dev.martyniuk.local.chat.core.R
import com.dev.martyniuk.local.chat.ui.view.databinding.FragmentAuthSignUpPhotoBinding
import com.dev.martyniuk.local.chat.ui.view.ext.onEach
import com.dev.martyniuk.local.chat.ui.view.ext.updateText
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FragmentSignUpPhoto : Fragment() {
    private lateinit var binding: FragmentAuthSignUpPhotoBinding
    private val viewModel: ViewModelSignUpPhoto by viewModels()

    private lateinit var commandOnUrlDonePressed: () -> Unit
    private lateinit var launcherGetPhoto: ActivityResultLauncher<String>

    override fun onCreateView(inflater: LayoutInflater, group: ViewGroup?, state: Bundle?): View =
        FragmentAuthSignUpPhotoBinding
            .inflate(inflater)
            .also {
                binding = it
                launcherGetPhoto = registerForActivityResult(
                    ActivityResultContracts.GetContent(),
                    viewModel::onUseDirectUriCommand
                )
            }
            .root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.bitmap.observe(viewLifecycleOwner, binding.imgCropView::setImageBitmap)

        binding.inputImgUrl.doAfterTextChanged { viewModel.onInputUrl(it.toString()) }
        binding.inputImgUrl.setOnEditorActionListener { it, action, _ ->
            if (action == EditorInfo.IME_ACTION_DONE) commandOnUrlDonePressed.invoke()
            it.clearFocus()
            false
        }

        viewModel.url.observe(viewLifecycleOwner, binding.inputImgUrl::updateText)
        viewModel.isUrlValid
            .onEach {
                commandOnUrlDonePressed =
                    if (it) viewModel::onUseUrlInputCommand
                    else { { } }
            }
            .map { if (it) null else getString(R.string.auth_img_file_url_invalid) }
            .observe(viewLifecycleOwner, binding.layoutImgUrl::setError)

        binding.btnSelectPhoto.setOnClickListener { launcherGetPhoto.launch("image/*") }
    }
}