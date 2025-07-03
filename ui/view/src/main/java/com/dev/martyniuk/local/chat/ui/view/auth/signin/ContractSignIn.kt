package com.dev.martyniuk.local.chat.ui.view.auth.signin

import androidx.lifecycle.LiveData

interface ContractSignIn {
    val emailLData: LiveData<String>
    val passwordLData: LiveData<String>

    fun onSignInCommand()
    fun onSignInWithGoogleCommand()
    fun onSignInWithMicrosoft()
    fun onSignInWithFacebook()
    fun onSignUpCommand()
    fun onRestoreAccountCommand()
}