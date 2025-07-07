package com.dev.martyniuk.local.chat.ui.view.root.auth

import dagger.hilt.android.scopes.ActivityRetainedScoped
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@ActivityRetainedScoped
class EventDispatcherAuth @Inject constructor() {
    private val scope = CoroutineScope(Dispatchers.Main.immediate)
    private val _flow = MutableSharedFlow<NavigationCommand?>(extraBufferCapacity = 1, replay = 0)
    val events: Flow<NavigationCommand?> get() = _flow

    fun send(command: NavigationCommand) = scope.launch { _flow.emit(command) }

    enum class NavigationCommand {
        SignInWithGoogle,
        SignInWithMicrosoft,
        SignInWithFacebook,
        SignUpCredentials,
        SignUpPhoto,
        RestoreAccount
    }
}