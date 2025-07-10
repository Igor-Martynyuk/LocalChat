package com.dev.martyniuk.local.chat.ui.view.root.auth.signup.photo

import android.graphics.Bitmap
import android.net.Uri
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ViewModelSignUpPhoto @Inject constructor() : ViewModel(), ContractSignUpPhoto {
    private val _bitmap = MutableLiveData<Bitmap?>(null)
    override val bitmap: LiveData<Bitmap?> = _bitmap

    fun onUriReceived(uri: Uri) {

    }
}