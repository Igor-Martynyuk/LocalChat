package com.dev.martyniuk.local.chat.ui.view.root.auth.signup

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.distinctUntilChanged
import androidx.lifecycle.map
import androidx.lifecycle.viewModelScope
import com.dev.martyniuk.local.chat.core.layer.domain.validation.CaseValidateEmail
import com.dev.martyniuk.local.chat.core.layer.domain.validation.CaseValidatePassword
import com.dev.martyniuk.local.chat.core.layer.domain.validation.CaseValidateDisplayName
import com.dev.martyniuk.local.chat.core.layer.ui.event.dispatcher.DispatcherNavigationAuth
import com.dev.martyniuk.local.chat.ui.view.ext.combineExt
import com.dev.martyniuk.local.chat.ui.view.root.auth.abstraction.ViewModelCredentials
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ViewModelSignUp @Inject constructor(
    private val eventDispatcher: DispatcherNavigationAuth,
    validateEmailCase: CaseValidateEmail,
    validatePasswordCase: CaseValidatePassword,
    validateDisplayNameCase: CaseValidateDisplayName
) : ViewModelCredentials(validateEmailCase, validatePasswordCase) {
    private val _displayName = MutableLiveData("")
    val displayName = _displayName.distinctUntilChanged()
    val isDisplayNameValid = displayName.map(validateDisplayNameCase::invoke)
    fun onInputDisplayName(value: String) { _displayName.value = value }

    private val _confirmation = MutableLiveData("")
    val confirmation = _confirmation.distinctUntilChanged()
    val isConfirmationValid = confirmation.map { it == pass.value }
    fun onInputPassConfirmation(value: String){ _confirmation.value = value }

    val isFormFilled = isEmailValid.combineExt(isConfirmationValid, viewModelScope.coroutineContext, Boolean::and)
    fun onNextCommand() = eventDispatcher.send(DispatcherNavigationAuth.Route.SignUpPhoto)

    fun onSignInCommand() = eventDispatcher.send(DispatcherNavigationAuth.Route.SignIn)
}