package com.dev.martyniuk.local.chat.core.layer.domain.validation

import com.dev.martyniuk.local.chat.core.layer.domain.validation.abstraction.CaseValidateString
import javax.inject.Singleton

@Singleton
class CaseValidatePassword : CaseValidateString("^(?=.*[A-Za-z])(?=.*\\d).{8,}\$")