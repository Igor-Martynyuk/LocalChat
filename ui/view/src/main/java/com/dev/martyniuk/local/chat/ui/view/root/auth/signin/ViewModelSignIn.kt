package com.dev.martyniuk.local.chat.ui.view.root.auth.signin

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.map
import androidx.lifecycle.viewModelScope
import com.dev.martyniuk.local.chat.core.common.extensions.ignore
import com.dev.martyniuk.local.chat.core.layer.domain.auth.CaseSignInAsync
import com.dev.martyniuk.local.chat.core.layer.domain.validation.CaseValidateEmail
import com.dev.martyniuk.local.chat.core.layer.domain.validation.CaseValidatePassword
import com.dev.martyniuk.local.chat.ui.view.ext.combineExt
import com.dev.martyniuk.local.chat.core.layer.ui.event.dispatcher.DispatcherAuthNavigation
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ViewModelSignIn @Inject constructor(
    private val eventDispatcher: DispatcherAuthNavigation,
    private val validateEmailCase: CaseValidateEmail,
    private val validatePasswordCase: CaseValidatePassword,
    private val signInCase: CaseSignInAsync
) : ViewModel(), ContractSignIn {
    private val context = viewModelScope.coroutineContext

    override val email = MutableLiveData("")
    override val isEmailValid = email.map { validateEmailCase.invoke(it) }
    private val _isEmailErrorEnabled = MutableLiveData(false)
    override val showEmailInputError = _isEmailErrorEnabled
        .combineExt(isEmailValid, context, ::validationWhenEnabled)
        .combineExt(email.map { it.isNotEmpty() }, context, ::validationWhenNotEmpty)


    override val password = MutableLiveData("")
    override val isPasswordValid = password.map { validatePasswordCase.invoke(it) }
    private val _isPasswordErrorEnabled = MutableLiveData(false)
    override val showPasswordInputError = _isPasswordErrorEnabled
        .combineExt(isPasswordValid, context, ::validationWhenEnabled)
        .combineExt(password.map { it.isNotEmpty() }, context, ::validationWhenNotEmpty)

    override val isSignInEnabled =
        isEmailValid.combineExt(isPasswordValid, context) { emailValid, passwordValid ->
            emailValid && passwordValid
        }

    fun enableEmailValidation() {
        _isEmailErrorEnabled.value = true
    }

    fun enablePasswordValidation() {
        _isPasswordErrorEnabled.value = true
    }

    private fun validationWhenEnabled(isEnabled: Boolean, isValid: Boolean) =
        isEnabled && isValid.not()

    private fun validationWhenNotEmpty(isInvalid: Boolean, isNotEmpty: Boolean) =
        isInvalid && isNotEmpty

    override fun onSignInWithGoogleCommand() =
        eventDispatcher.send(DispatcherAuthNavigation.Route.SignInWithGoogle).ignore()

    override fun onSignInWithMicrosoft() =
        eventDispatcher.send(DispatcherAuthNavigation.Route.SignInWithMicrosoft).ignore()

    override fun onSignInWithFacebook() =
        eventDispatcher.send(DispatcherAuthNavigation.Route.SignInWithFacebook).ignore()

    override fun onSignUpCommand() =
        eventDispatcher.send(DispatcherAuthNavigation.Route.SignUpCredentials).ignore()

    override fun onRestoreAccountCommand() =
        eventDispatcher.send(DispatcherAuthNavigation.Route.RestoreAccount).ignore()

    override fun onSignInCommand() = viewModelScope.launch {
        signInCase.flow(CaseSignInAsync.Args(email.value!!, password.value!!)).collect { }
    }.ignore()
}