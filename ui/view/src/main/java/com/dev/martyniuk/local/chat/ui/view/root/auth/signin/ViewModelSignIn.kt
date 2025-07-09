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
import com.dev.martyniuk.local.chat.core.layer.ui.event.dispatcher.DispatcherNavigationAuth
import com.dev.martyniuk.local.chat.ui.view.root.auth.abstraction.ViewModelCredentials
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ViewModelSignIn @Inject constructor(
    private val eventDispatcher: DispatcherNavigationAuth,
    private val signInCase: CaseSignInAsync,
    validateEmailCase: CaseValidateEmail,
    validatePasswordCase: CaseValidatePassword
) : ViewModelCredentials(validateEmailCase, validatePasswordCase), ContractSignIn {

    override val isSignInEnabled =
        isEmailValid.combineExt(isPasswordValid, context) { emailValid, passwordValid ->
            emailValid && passwordValid
        }

    override fun onSignInWithGoogleCommand() =
        eventDispatcher.send(DispatcherNavigationAuth.Route.SignInWithGoogle).ignore()

    override fun onSignInWithMicrosoft() =
        eventDispatcher.send(DispatcherNavigationAuth.Route.SignInWithMicrosoft).ignore()

    override fun onSignInWithFacebook() =
        eventDispatcher.send(DispatcherNavigationAuth.Route.SignInWithFacebook).ignore()

    override fun onSignUpCommand() =
        eventDispatcher.send(DispatcherNavigationAuth.Route.SignUpCredentials).ignore()

    override fun onRestoreAccountCommand() =
        eventDispatcher.send(DispatcherNavigationAuth.Route.RestoreAccount).ignore()

    override fun onSignInCommand() = viewModelScope.launch {
        signInCase.flow(CaseSignInAsync.Args(email.value!!, password.value!!)).collect { }
    }.ignore()
}