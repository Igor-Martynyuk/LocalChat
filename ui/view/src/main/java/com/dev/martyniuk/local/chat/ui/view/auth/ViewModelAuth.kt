package com.dev.martyniuk.local.chat.ui.view.auth

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.dev.martyniuk.local.chat.core.layer.domain.auth.CaseSubscribeIsAuthorized
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ViewModelAuth @Inject constructor(
    eventDispatcher: EventDispatcherAuth,
    subscribeIsAuthorizedCase: CaseSubscribeIsAuthorized
) : ViewModel() {
    val events = eventDispatcher.events.asLiveData()

    private val _isAuthorized = MutableSharedFlow<Boolean>(extraBufferCapacity = 1, replay = 0)
    val isAuthorized: LiveData<Boolean> get() = _isAuthorized.asLiveData()


    init {
        viewModelScope.launch {
            subscribeIsAuthorizedCase.getFlow(Unit).collect(_isAuthorized::emit)
        }
    }
}