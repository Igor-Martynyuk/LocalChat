package com.dev.martyniuk.local.chat.core.layer.domain.validation

import com.dev.martyniuk.local.chat.core.layer.domain.validation.abstraction.CaseValidateString
import javax.inject.Singleton

@Singleton
class CaseValidateEmail : CaseValidateString("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}\$")