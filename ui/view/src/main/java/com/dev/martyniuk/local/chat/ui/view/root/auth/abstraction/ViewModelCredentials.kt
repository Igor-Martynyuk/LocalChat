package com.dev.martyniuk.local.chat.ui.view.root.auth.abstraction

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.map
import androidx.lifecycle.viewModelScope
import com.dev.martyniuk.local.chat.core.layer.domain.validation.CaseValidateEmail
import com.dev.martyniuk.local.chat.core.layer.domain.validation.CaseValidatePassword
import com.dev.martyniuk.local.chat.ui.view.ext.combineExt

abstract class ViewModelCredentials(
    private val validateEmailCase: CaseValidateEmail,
    private val validatePasswordCase: CaseValidatePassword
) : ViewModel(), ContractCredentials {
    protected val context = viewModelScope.coroutineContext

    final override val email = MutableLiveData("")
    final override val isEmailValid = email.map { validateEmailCase.invoke(it) }
    private val _isEmailErrorEnabled = MutableLiveData(false)
    override val showEmailInputError = _isEmailErrorEnabled
        .combineExt(isEmailValid, context, ::validationWhenEnabled)
        .combineExt(email.map { it.isNotEmpty() }, context, ::validationWhenNotEmpty)


    final override val password = MutableLiveData("")
    final override val isPasswordValid = password.map { validatePasswordCase.invoke(it) }
    private val _isPasswordErrorEnabled = MutableLiveData(false)
    override val showPasswordInputError = _isPasswordErrorEnabled
        .combineExt(isPasswordValid, context, ::validationWhenEnabled)
        .combineExt(password.map { it.isNotEmpty() }, context, ::validationWhenNotEmpty)

    private fun validationWhenEnabled(isEnabled: Boolean, isValid: Boolean) =
        isEnabled && isValid.not()

    private fun validationWhenNotEmpty(isInvalid: Boolean, isNotEmpty: Boolean) =
        isInvalid && isNotEmpty

    fun enableEmailValidation() {
        _isEmailErrorEnabled.value = true
    }

    fun enablePasswordValidation() {
        _isPasswordErrorEnabled.value = true
    }
}