package com.dev.martyniuk.local.chat.ui.view.root.auth.signin

import androidx.lifecycle.LiveData
import com.dev.martyniuk.local.chat.ui.view.root.auth.abstraction.ContractCredentials

interface ContractSignIn : ContractCredentials {
    val isSignInEnabled: LiveData<Boolean>

    fun onSignInCommand()
    fun onSignInWithGoogleCommand()
    fun onSignInWithMicrosoft()
    fun onSignInWithFacebook()
    fun onSignUpCommand()
    fun onRestoreAccountCommand()
}