package com.dev.martyniuk.local.chat.ui.view.auth.signin

import android.util.Log
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asFlow
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.dev.martyniuk.local.chat.core.common.extensions.ignore
import com.dev.martyniuk.local.chat.core.layer.domain.auth.CaseSignIn
import com.dev.martyniuk.local.chat.ui.view.auth.EventDispatcherAuth
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ViewModelSignIn @Inject constructor(
    private val eventDispatcher: EventDispatcherAuth,
    private val signInCase: CaseSignIn
) : ViewModel(), ContractSignIn {

    private val emailStateFlow = MutableStateFlow("")
    override val emailLData: LiveData<String> get() = emailStateFlow.asLiveData()

    private val passwordStateFlow = MutableStateFlow("")
    override val passwordLData: LiveData<String> get() = passwordStateFlow.asLiveData()

    init {
        viewModelScope.launch {
            emailStateFlow.collect { Log.d("temp_log", "email:$it") }
        }
        viewModelScope.launch {
            passwordStateFlow.collect { Log.d("temp_log", "password:$it") }
        }
    }

    fun onEmailInput(value: String) {
        emailStateFlow.value = value
    }

    fun onPasswordInput(value: String) {
        passwordStateFlow.value = value
    }

    override fun onSignInCommand() = viewModelScope.launch {
        signInCase
            .getFlow(CaseSignIn.Args("email", "password"))
            .collect { }
    }.ignore()

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
}