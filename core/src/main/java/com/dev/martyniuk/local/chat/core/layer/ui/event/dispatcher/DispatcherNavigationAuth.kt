package com.dev.martyniuk.local.chat.core.layer.ui.event.dispatcher

import com.dev.martyniuk.local.chat.core.layer.ui.event.dispatcher.DispatcherNavigationAuth.*
import dagger.hilt.android.scopes.ActivityRetainedScoped
import dagger.hilt.android.scopes.ViewModelScoped
import javax.inject.Inject

@ActivityRetainedScoped
class DispatcherNavigationAuth @Inject constructor() : UiEventDispatcher<Route>() {
    enum class Route {
        SignInWithGoogle,
        SignInWithMicrosoft,
        SignInWithFacebook,
        SignUpCredentials,
        SignUpPhoto,
        RestoreAccount
    }
}