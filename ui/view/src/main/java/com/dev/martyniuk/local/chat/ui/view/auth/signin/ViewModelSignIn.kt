package com.dev.martyniuk.local.chat.ui.view.auth.signin

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dev.martyniuk.local.chat.core.layer.domain.auth.CaseSignIn
import com.dev.martyniuk.local.chat.ui.view.auth.EventDispatcherAuth
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ViewModelSignIn @Inject constructor(
    private val eventDispatcher: EventDispatcherAuth,
    private val signInCase: CaseSignIn
) : ViewModel() {

    fun onSignInCommand(email: String, password: String) = viewModelScope.launch {
        signInCase
            .getFlow(CaseSignIn.Args(email, password))
            .collect { }
    }

    fun onSignInWithGoogleCommand() =
        eventDispatcher.send(EventDispatcherAuth.NavigationCommand.SignInWithGoogle)

    fun onSignInWithMicrosoft() =
        eventDispatcher.send(EventDispatcherAuth.NavigationCommand.SignInWithMicrosoft)

    fun onSignInWithFacebook() =
        eventDispatcher.send(EventDispatcherAuth.NavigationCommand.SignInWithFacebook)

    fun onSignUpCommand() =
        eventDispatcher.send(EventDispatcherAuth.NavigationCommand.SignUpCredentials)

    fun onRestoreAccountCommand() =
        eventDispatcher.send(EventDispatcherAuth.NavigationCommand.RestoreAccount)
}