package com.dev.martyniuk.local.chat.ui.view.root.auth.signup.photo

import android.Manifest
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.FileProvider
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.map
import com.dev.martyniuk.local.chat.core.R
import com.dev.martyniuk.local.chat.core.doNothing
import com.dev.martyniuk.local.chat.core.extensions.android.isGranted
import com.dev.martyniuk.local.chat.ui.view.databinding.FragmentAuthSignUpPhotoBinding
import com.dev.martyniuk.local.chat.ui.view.ext.onEach
import com.dev.martyniuk.local.chat.ui.view.ext.updateText
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import java.io.File

@AndroidEntryPoint
class FragmentSignUpPhoto : Fragment() {
    private var prefixPhotoName = "photo_"
    private var routeProvider = ".provider"

    private lateinit var binding: FragmentAuthSignUpPhotoBinding
    private val viewModel: ViewModelSignUpPhoto by viewModels()

    private var onUrlInputDone: () -> Unit = ::doNothing
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

    private fun setupMakePhotoRequest() {
        val onCameraDenied = ::notifyCameraPermissionDeclined
        val onCameraPermitted = {
            val file = File(
                requireContext().getExternalFilesDir(Environment.DIRECTORY_PICTURES),
                "$prefixPhotoName${System.currentTimeMillis()}.${Bitmap.CompressFormat.JPEG}"
            )

            photoUri = FileProvider.getUriForFile(requireContext(), "${requireContext().packageName}$routeProvider", file)
            photoUri?.let(launcherCamera::launch) ?: notifyCantCreateFile()
        }

        launcherRequestCameraPermission =
            registerForActivityResult(ActivityResultContracts.RequestPermission()) {
                if (it) onCameraPermitted() else onCameraDenied()
            }

        launcherCamera = registerForActivityResult(ActivityResultContracts.TakePicture()) {
            if (it) binding.imgCropView.setImageUriAsync(photoUri)
            else notifyTakePhotoFailed()
        }

        binding.btnTakePhoto.setOnClickListener {
            if (requireContext().isGranted(Manifest.permission.CAMERA)) onCameraPermitted()
            else launcherRequestCameraPermission.launch(Manifest.permission.CAMERA)
        }
    }

    private fun showTextNotification(resId: Int) =
        Snackbar.make(requireView(), getString(resId), Snackbar.LENGTH_LONG).show()

    private fun notifyCameraPermissionDeclined() =
        showTextNotification(R.string.notification_permission_declined_camera)

    private fun notifyTakePhotoFailed() =
        showTextNotification(R.string.notification_failed_make_photo)

    private fun notifyCantCreateFile() =
        showTextNotification(R.string.notification_failed_make_photo_file)

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
            if (action == EditorInfo.IME_ACTION_DONE) onUrlInputDone()
            it.clearFocus()
            false
        }

        viewModel.url.observe(viewLifecycleOwner, binding.inputImgUrl::updateText)
        viewModel.isUrlValid
            .onEach { onUrlInputDone = if (it) viewModel::onUseUrlInputCommand else ::doNothing }
            .map { if (it) null else getString(R.string.auth_img_file_url_invalid) }
            .observe(viewLifecycleOwner, binding.layoutImgUrl::setError)
    }
}