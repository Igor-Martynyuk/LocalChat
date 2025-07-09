package com.dev.martyniuk.local.chat.ui.view.root.auth.signup.credentials

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.map
import com.dev.martyniuk.local.chat.core.common.extensions.ignore
import com.dev.martyniuk.local.chat.core.layer.domain.validation.CaseValidateEmail
import com.dev.martyniuk.local.chat.core.layer.domain.validation.CaseValidatePassword
import com.dev.martyniuk.local.chat.core.layer.ui.event.dispatcher.DispatcherNavigationAuth
import com.dev.martyniuk.local.chat.ui.view.ext.combineExt
import com.dev.martyniuk.local.chat.ui.view.root.auth.abstraction.ViewModelCredentials
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ViewModelSignUpCredentials @Inject constructor(
    private val eventDispatcher: DispatcherNavigationAuth,
    validateEmailCase: CaseValidateEmail,
    validatePasswordCase: CaseValidatePassword
) : ViewModelCredentials(validateEmailCase, validatePasswordCase), ContractSignUpCredentials {
    override val confirmation = MutableLiveData("")
    private val isConfirmValid = confirmation.combineExt(password, context, ::confirmPassword)
    private val isConfirmValidationEnabled = MutableLiveData(false)

    override val showConfirmInputError = isConfirmValidationEnabled
        .combineExt(isConfirmValid.map { it.not() }, context, ::and)
        .combineExt(confirmation.map { it.isNotEmpty() }, context, ::and)

    override val isNextStepEnabled = isEmailValid
        .combineExt(isPasswordValid, context, ::and)
        .combineExt(isConfirmValid, context, ::and)

    private fun and(first: Boolean, second: Boolean) = first && second
    private fun confirmPassword(pass: String, confirmation: String) = pass == confirmation

    override fun enableConfirmValidation() {
        isConfirmValidationEnabled.value = true
    }

    override fun onNextStepCommand() =
        eventDispatcher.send(DispatcherNavigationAuth.Route.SignUpPhoto).ignore()
}