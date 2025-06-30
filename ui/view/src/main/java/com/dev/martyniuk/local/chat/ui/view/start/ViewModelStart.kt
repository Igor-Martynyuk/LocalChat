package com.dev.martyniuk.local.chat.ui.view.start

import androidx.lifecycle.ViewModel
import com.dev.martyniuk.local.chat.core.layer.domain.auth.CaseSubscribeIsAuthorized
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ViewModelStart @Inject constructor(
    private val observeAuthorizationCase: CaseSubscribeIsAuthorized
) : ViewModel() {

}