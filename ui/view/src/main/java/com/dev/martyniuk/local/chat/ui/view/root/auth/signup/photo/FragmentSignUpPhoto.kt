package com.dev.martyniuk.local.chat.ui.view.root.auth.signup.photo

import android.Manifest
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.FileProvider
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.map
import com.dev.martyniuk.local.chat.core.R
import com.dev.martyniuk.local.chat.core.extensions.isNull
import com.dev.martyniuk.local.chat.ui.view.databinding.FragmentAuthSignUpPhotoBinding
import com.dev.martyniuk.local.chat.ui.view.ext.onEach
import com.dev.martyniuk.local.chat.ui.view.ext.updateText
import dagger.hilt.android.AndroidEntryPoint
import java.io.File

@AndroidEntryPoint
class FragmentSignUpPhoto : Fragment() {
    private lateinit var binding: FragmentAuthSignUpPhotoBinding
    private val viewModel: ViewModelSignUpPhoto by viewModels()

    private lateinit var commandOnUrlInputDone: () -> Unit
    private lateinit var launcherGetPhoto: ActivityResultLauncher<String>

    private lateinit var launcherRequestCameraPermission: ActivityResultLauncher<String>
    private lateinit var launcherCamera: ActivityResultLauncher<Uri>
    private var photoUri: Uri? = null

    override fun onCreateView(inflater: LayoutInflater, group: ViewGroup?, state: Bundle?): View =
        FragmentAuthSignUpPhotoBinding.inflate(inflater).also { binding = it }.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.bitmap.observe(viewLifecycleOwner, binding.imgCropView::setImageBitmap)

        setupUrlInputField()
        setupGetImageRequest()
        setupMakePhotoRequest()
    }

    private fun openCamera() {
        val photoFile = File(
            requireContext().getExternalFilesDir(Environment.DIRECTORY_PICTURES),
            "photo_${System.currentTimeMillis()}.jpg"
        )

        photoUri = FileProvider.getUriForFile(
            requireContext(),
            "${requireContext().packageName}.provider",
            photoFile
        )

        if (photoUri.isNull()) Toast.makeText(requireContext(), "Can't create photo file", Toast.LENGTH_LONG).show()
        else launcherCamera.launch(photoUri!!)
    }

    private fun setupMakePhotoRequest() {
        val commandOnCameraDenied =
            Toast.makeText(requireContext(), "Permissions denied", Toast.LENGTH_LONG)::show
        val commandOnCameraPermitted = ::openCamera

        launcherRequestCameraPermission = registerForActivityResult(ActivityResultContracts.RequestPermission()) {
            if (it) commandOnCameraPermitted.invoke()
            else commandOnCameraDenied.invoke()
        }

        launcherCamera = registerForActivityResult(ActivityResultContracts.TakePicture()) {
            if (it) binding.imgCropView.setImageUriAsync(photoUri)
            else Toast.makeText(requireContext(), "Canceled", Toast.LENGTH_LONG).show()
        }

        binding.btnTakePhoto.setOnClickListener {
            requestCameraPermission(commandOnCameraPermitted)
        }
    }

    private fun requestCameraPermission(onPermitted: () -> Unit) =
        if (requireContext().checkSelfPermission(Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED) onPermitted.invoke()
        else launcherRequestCameraPermission.launch(Manifest.permission.CAMERA)

    private fun setupGetImageRequest() {
        launcherGetPhoto = registerForActivityResult(
            ActivityResultContracts.GetContent(),
            viewModel::onUseDirectUriCommand
        )

        binding.btnSelectPhoto.setOnClickListener { launcherGetPhoto.launch("image/*") }
    }

    private fun setupUrlInputField() {
        binding.inputImgUrl.doAfterTextChanged { viewModel.onInputUrl(it.toString()) }
        binding.inputImgUrl.setOnEditorActionListener { it, action, _ ->
            if (action == EditorInfo.IME_ACTION_DONE) commandOnUrlInputDone.invoke()
            it.clearFocus()
            false
        }

        viewModel.url.observe(viewLifecycleOwner, binding.inputImgUrl::updateText)
        viewModel.isUrlValid
            .onEach {
                commandOnUrlInputDone =
                    if (it) viewModel::onUseUrlInputCommand
                    else {
                        { }
                    }
            }
            .map { if (it) null else getString(R.string.auth_img_file_url_invalid) }
            .observe(viewLifecycleOwner, binding.layoutImgUrl::setError)
    }
}