package com.dev.martyniuk.local.chat.core.layer.ui.event.dispatcher

import com.dev.martyniuk.local.chat.core.layer.ui.event.dispatcher.DispatcherAuthNavigation.*
import dagger.hilt.android.scopes.ViewModelScoped
import javax.inject.Inject

@ViewModelScoped
class DispatcherAuthNavigation @Inject constructor() : UiEventDispatcher<Route>() {
    enum class Route {
        SignInWithGoogle,
        SignInWithMicrosoft,
        SignInWithFacebook,
        SignUpCredentials,
        SignUpPhoto,
        RestoreAccount
    }
}