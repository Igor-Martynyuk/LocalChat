package com.dev.martyniuk.local.chat.ui.view.root.auth.signup.credentials

import androidx.lifecycle.ViewModel
import com.dev.martyniuk.local.chat.core.layer.ui.event.dispatcher.DispatcherNavigationAuth
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ViewModelSignUpCredentials @Inject constructor(
    private val eventDispatcher: DispatcherNavigationAuth
) : ViewModel(), ContractSignUpCredentials {

    fun onSetupPhotoCommand() =
        eventDispatcher.send(DispatcherNavigationAuth.Route.SignUpPhoto)

}