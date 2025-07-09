package com.dev.martyniuk.local.chat.core.layer.domain.validation

import dagger.hilt.android.scopes.ViewModelScoped
import javax.inject.Inject

@ViewModelScoped
class CaseValidatePassword @Inject constructor() :
    CaseValidateString("^(?=.*[A-Za-z])(?=.*\\d).{8,}\$")