package com.dev.martyniuk.local.chat.ui.view.root.auth

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.distinctUntilChanged
import androidx.lifecycle.map
import com.dev.martyniuk.local.chat.core.layer.domain.interactor.validation.CaseValidateEmail
import com.dev.martyniuk.local.chat.core.layer.domain.interactor.validation.CaseValidatePassword

abstract class ViewModelCredentials(
    private val validateEmailCase: CaseValidateEmail,
    private val validatePasswordCase: CaseValidatePassword
) : ViewModel() {
    private val _email = MutableLiveData("")
    val email = _email.distinctUntilChanged()
    val isEmailValid = email.map(validateEmailCase::invoke).distinctUntilChanged()
    fun onInputEmail(value: String) { _email.value = value }

    private val _pass = MutableLiveData("")
    val pass = _pass.distinctUntilChanged()
    val isPassValid = pass.map(validatePasswordCase::invoke).distinctUntilChanged()
    fun onInputPassword(value: String) { _pass.value = value }
}