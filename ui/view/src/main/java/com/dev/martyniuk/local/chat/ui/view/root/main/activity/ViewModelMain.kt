package com.dev.martyniuk.local.chat.ui.view.root.main.activity

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dev.martyniuk.local.chat.core.layer.domain.interactor.auth.CaseSubscribeIsAuthorized
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ViewModelMain @Inject constructor(
    private val observeAuthorizationCase: CaseSubscribeIsAuthorized
) : ViewModel() {
    private val _isAuthenticated: MutableLiveData<Boolean?> = MutableLiveData(null)
    val isAuthenticated: LiveData<Boolean?> = _isAuthenticated

    init {
        viewModelScope.launch {
            observeAuthorizationCase
                .invoke(Unit)
                .flowOn(Dispatchers.IO)
                .collect { _isAuthenticated.value = it }
        }
    }
}