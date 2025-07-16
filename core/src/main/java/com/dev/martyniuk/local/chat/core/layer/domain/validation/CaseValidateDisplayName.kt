package com.dev.martyniuk.local.chat.core.layer.domain.validation

import dagger.hilt.android.scopes.ViewModelScoped
import javax.inject.Inject

@ViewModelScoped
class CaseValidateDisplayName @Inject constructor() : CaseValidateString(
    "^(?=.*\\p{L}).*\$"
)