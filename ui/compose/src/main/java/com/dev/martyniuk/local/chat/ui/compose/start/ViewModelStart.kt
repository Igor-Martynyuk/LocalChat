package com.dev.martyniuk.local.chat.ui.compose.start

import androidx.lifecycle.ViewModel
import com.dev.martyniuk.local.chat.core.layer.domain.auth.CaseSubscribeIsAuthorized
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ViewModelStart @Inject constructor(
    private val subscribeIsAuthorizedCase: CaseSubscribeIsAuthorized
) : ViewModel() {

}