package com.dev.martyniuk.local.chat.ui.view.root.auth

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.dev.martyniuk.local.chat.core.layer.domain.interactor.auth.CaseSubscribeIsAuthorized
import com.dev.martyniuk.local.chat.core.layer.domain.event.dispatcher.DispatcherNavigationAuth
import com.dev.martyniuk.local.chat.ui.view.ext.asLiveEventExt
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ViewModelAuth @Inject constructor(
    eventDispatcher: DispatcherNavigationAuth,
    subscribeIsAuthorizedCase: CaseSubscribeIsAuthorized
) : ViewModel() {
    val events = eventDispatcher.flow.asLiveEventExt()

    private val _isAuthorized = MutableSharedFlow<Boolean>(extraBufferCapacity = 1, replay = 0)
    val isAuthorized = _isAuthorized.asLiveData()


    init {
        viewModelScope.launch {
            subscribeIsAuthorizedCase.invoke(Unit).collect(_isAuthorized::emit)
        }
    }
}