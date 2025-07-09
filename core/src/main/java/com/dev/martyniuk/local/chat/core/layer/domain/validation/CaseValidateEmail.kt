package com.dev.martyniuk.local.chat.core.layer.domain.validation

import dagger.hilt.android.scopes.ViewModelScoped
import javax.inject.Inject

@ViewModelScoped
class CaseValidateEmail @Inject constructor() :
    CaseValidateString("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}\$")