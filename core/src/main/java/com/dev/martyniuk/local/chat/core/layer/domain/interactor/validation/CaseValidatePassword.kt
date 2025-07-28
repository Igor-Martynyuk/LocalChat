package com.dev.martyniuk.local.chat.core.layer.domain.interactor.validation

import dagger.hilt.android.scopes.ViewModelScoped
import javax.inject.Inject

@ViewModelScoped
class CaseValidatePassword @Inject constructor() :
    CaseValidateString("^(?=.*[A-Za-z])(?=.*\\d).{8,}\$")