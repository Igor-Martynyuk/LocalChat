package com.dev.martyniuk.local.chat.core.layer.domain.validation

import dagger.hilt.android.scopes.ViewModelScoped
import javax.inject.Inject

@ViewModelScoped
class CaseValidateImgUrl @Inject constructor() : CaseValidateString("^(https?|ftp)://[^\\s]+\$")