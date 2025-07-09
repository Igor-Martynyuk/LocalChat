package com.dev.martyniuk.local.chat.ui.view.root.auth.abstraction

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

interface ContractCredentials {
    val email: MutableLiveData<String>
    val isEmailValid: LiveData<Boolean>
    val showEmailInputError: LiveData<Boolean>

    val password: MutableLiveData<String>
    val isPasswordValid: LiveData<Boolean>
    val showPasswordInputError: LiveData<Boolean>
}