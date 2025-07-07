package com.dev.martyniuk.local.chat.ui.view.root.auth.signin

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

interface ContractSignIn {
    val email: MutableLiveData<String>
    val isEmailValid: LiveData<Boolean>
    val password: MutableLiveData<String>
    val isPasswordValid: LiveData<Boolean>
    val isLoginEnabled: LiveData<Boolean>

    fun onSignInCommand()
    fun onSignInWithGoogleCommand()
    fun onSignInWithMicrosoft()
    fun onSignInWithFacebook()
    fun onSignUpCommand()
    fun onRestoreAccountCommand()
}