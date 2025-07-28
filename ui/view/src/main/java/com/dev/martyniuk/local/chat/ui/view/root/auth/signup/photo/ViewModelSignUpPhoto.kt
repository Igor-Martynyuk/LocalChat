package com.dev.martyniuk.local.chat.ui.view.root.auth.signup.photo

import android.graphics.Bitmap
import android.net.Uri
import androidx.core.net.toUri
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.distinctUntilChanged
import androidx.lifecycle.map
import androidx.lifecycle.viewModelScope
import com.dev.martyniuk.local.chat.core.layer.domain.interactor.img.CaseLoadBitmap
import com.dev.martyniuk.local.chat.core.layer.domain.interactor.validation.CaseValidateImgUrl
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ViewModelSignUpPhoto @Inject constructor(
    private val caseValidateImgUrl: CaseValidateImgUrl,
    private val caseLoadBitmap: CaseLoadBitmap
) : ViewModel() {
    private val _bitmap = MutableLiveData<Bitmap?>(null)
    val bitmap: LiveData<Bitmap?> = _bitmap

    private val _url = MutableLiveData("")
    val url = _url.distinctUntilChanged()
    val isUrlValid = url.map(caseValidateImgUrl::invoke)
    fun onInputUrl(value: String) { _url.value = value }

    fun onUseUrlInputCommand() = onUseDirectUriCommand(_url.value?.toUri())
    fun onUseDirectUriCommand(uri: Uri?) = uri?.let {
        viewModelScope.launch {
            caseLoadBitmap.buildFlow(it)
                .flowOn(Dispatchers.IO)
                .collect { _bitmap.postValue(it) }
        }
    }
}