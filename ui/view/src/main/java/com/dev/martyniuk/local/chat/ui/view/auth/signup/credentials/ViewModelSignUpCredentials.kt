package com.dev.martyniuk.local.chat.ui.view.auth.signup.credentials

import androidx.lifecycle.ViewModel
import com.dev.martyniuk.local.chat.ui.view.auth.EventDispatcherAuth
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ViewModelSignUpCredentials @Inject constructor(
    val eventDispatcher: EventDispatcherAuth
) : ViewModel() {

    fun onSetupPhotoCommand() =
        eventDispatcher.send(EventDispatcherAuth.NavigationCommand.SignUpPhoto)

}