package com.dev.martyniuk.local.chat.ui.view.auth.signin

interface ContractSignIn {
    fun onSignInCommand(email: String, password: String): Unit
    fun onSignInWithGoogleCommand(): Unit
    fun onSignInWithMicrosoft(): Unit
    fun onSignInWithFacebook(): Unit
    fun onSignUpCommand(): Unit
    fun onRestoreAccountCommand(): Unit
}