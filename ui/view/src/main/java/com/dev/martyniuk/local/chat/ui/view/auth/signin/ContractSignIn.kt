package com.dev.martyniuk.local.chat.ui.view.auth.signin

interface ContractSignIn {
    fun onSignInCommand()
    fun onSignInWithGoogleCommand()
    fun onSignInWithMicrosoft()
    fun onSignInWithFacebook()
    fun onSignUpCommand()
    fun onRestoreAccountCommand()
}