package com.dev.martyniuk.local.chat.ui.view.root.auth.signup.credentials

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.dev.martyniuk.local.chat.ui.view.root.auth.abstraction.ContractCredentials

interface ContractSignUpCredentials : ContractCredentials {
    val confirmation: MutableLiveData<String>
    val showConfirmInputError: LiveData<Boolean>
    val isNextStepEnabled: LiveData<Boolean>

    fun enableConfirmValidation()
    fun onNextStepCommand()
}