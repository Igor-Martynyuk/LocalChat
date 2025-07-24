package com.dev.martyniuk.local.chat.ui.view.root.auth.signin

import androidx.lifecycle.viewModelScope
import com.dev.martyniuk.local.chat.core.layer.domain.auth.CaseSignInAsync
import com.dev.martyniuk.local.chat.core.layer.domain.validation.CaseValidateEmail
import com.dev.martyniuk.local.chat.core.layer.domain.validation.CaseValidatePassword
import com.dev.martyniuk.local.chat.core.layer.ui.event.dispatcher.DispatcherNavigationAuth
import com.dev.martyniuk.local.chat.core.layer.ui.event.dispatcher.DispatcherNavigationAuth.Route
import com.dev.martyniuk.local.chat.ui.view.ext.combineExt
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
) : ViewModelCredentials(validateEmailCase, validatePasswordCase) {
    val isSignInEnabled = isEmailValid
        .combineExt(isPassValid, viewModelScope.coroutineContext, Boolean::and)

    fun onSignInWithGoogleCommand() = eventDispatcher.send(Route.SignInWithGoogle)
    fun onSignInWithMicrosoft() = eventDispatcher.send(Route.SignInWithMicrosoft)
    fun onSignInWithFacebook() = eventDispatcher.send(Route.SignInWithFacebook)
    fun onSignUpCommand() = eventDispatcher.send(Route.SignUp)
    fun onRestoreAccountCommand() = eventDispatcher.send(Route.RestoreAccount)

    fun onSignInCommand() = viewModelScope
        .launch { signInCase.flow(CaseSignInAsync.Args(email.value!!, pass.value!!)).collect { } }
}