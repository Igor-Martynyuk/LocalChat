package com.dev.martyniuk.local.chat.ui.view.root.auth.signup.photo

import android.graphics.Bitmap
import android.net.Uri
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dev.martyniuk.local.chat.core.layer.domain.img.CaseLoadBitmap
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ViewModelSignUpPhoto @Inject constructor(
    private val caseLoadBitmap: CaseLoadBitmap
) : ViewModel() {
    private val _bitmap = MutableLiveData<Bitmap?>(null)
    val bitmap: LiveData<Bitmap?> = _bitmap

    fun onUriReceived(uri: Uri?) = uri?.let {
        viewModelScope.launch {
            caseLoadBitmap.buildFlow(it).collect { _bitmap.postValue(it) }
        }
    }
}