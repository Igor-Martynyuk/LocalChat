package com.dev.martyniuk.local.chat.ui.view.root.auth.signin

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.map
import androidx.lifecycle.viewModelScope
import com.dev.martyniuk.local.chat.core.common.extensions.ignore
import com.dev.martyniuk.local.chat.core.layer.domain.auth.CaseSignIn
import com.dev.martyniuk.local.chat.ui.view.ext.combineExt
import com.dev.martyniuk.local.chat.ui.view.root.auth.EventDispatcherAuth
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ViewModelSignIn @Inject constructor(
    private val eventDispatcher: EventDispatcherAuth,
    private val signInCase: CaseSignIn
) : ViewModel(), ContractSignIn {
    private val context = viewModelScope.coroutineContext
    private val regexEmail = Regex("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}\$")
    private val regexPassword = Regex("^(?=.*[A-Za-z])(?=.*\\d).{8,}\$")

    override val email = MutableLiveData("")
    override val isEmailValid = email.map { it.matches(regexEmail) }
    private val _isEmailErrorEnabled = MutableLiveData(false)
    override val showEmailInputError = _isEmailErrorEnabled
        .combineExt(isEmailValid, context, ::validationWhenEnabled)
        .combineExt(email.map { it.isNotEmpty() }, context, ::validationWhenNotEmpty)


    override val password = MutableLiveData("")
    override val isPasswordValid = password.map { it.matches(regexPassword) }
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
        eventDispatcher.send(EventDispatcherAuth.NavigationCommand.SignInWithGoogle).ignore()

    override fun onSignInWithMicrosoft() =
        eventDispatcher.send(EventDispatcherAuth.NavigationCommand.SignInWithMicrosoft).ignore()

    override fun onSignInWithFacebook() =
        eventDispatcher.send(EventDispatcherAuth.NavigationCommand.SignInWithFacebook).ignore()

    override fun onSignUpCommand() =
        eventDispatcher.send(EventDispatcherAuth.NavigationCommand.SignUpCredentials).ignore()

    override fun onRestoreAccountCommand() =
        eventDispatcher.send(EventDispatcherAuth.NavigationCommand.RestoreAccount).ignore()

    override fun onSignInCommand() = viewModelScope.launch {
        signInCase
            .getFlow(CaseSignIn.Args(email.value!!, password.value!!))
            .collect { }
    }.ignore()
}