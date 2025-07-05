package com.dev.martyniuk.local.chat.ui.view.auth.signin

import androidx.lifecycle.MutableLiveData

interface ContractSignIn {
    val emailLData: MutableLiveData<String>
    val passwordLData: MutableLiveData<String>

    fun onSignInCommand()
    fun onSignInWithGoogleCommand()
    fun onSignInWithMicrosoft()
    fun onSignInWithFacebook()
    fun onSignUpCommand()
    fun onRestoreAccountCommand()
}