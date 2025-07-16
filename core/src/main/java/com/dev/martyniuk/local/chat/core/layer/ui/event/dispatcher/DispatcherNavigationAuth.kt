package com.dev.martyniuk.local.chat.core.layer.ui.event.dispatcher

import com.dev.martyniuk.local.chat.core.layer.ui.event.dispatcher.DispatcherNavigationAuth.*
import dagger.hilt.android.scopes.ActivityRetainedScoped
import javax.inject.Inject

@ActivityRetainedScoped
class DispatcherNavigationAuth @Inject constructor() : UiEventDispatcher<Route>() {
    enum class Route {
        SignIn,
        SignInWithGoogle,
        SignInWithMicrosoft,
        SignInWithFacebook,
        SignUp,
        SignUpPhoto,
        RestoreAccount
    }
}