package com.dev.martyniuk.local.chat.core.layer.domain.interactor.auth

import com.dev.martyniuk.local.chat.core.layer.domain.interactor.UseCase
import com.dev.martyniuk.local.chat.core.layer.domain.interactor.auth.dto.DtoUserAccount
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

@ViewModelScoped
class CaseSubscribeIsAuthorized @Inject constructor(
    private val localPort: LocalPort
) : UseCase<Unit, Flow<Boolean>>() {
    interface LocalPort {
        fun subscribeLoggedUser(): Flow<DtoUserAccount?>
    }

    override fun invoke(args: Unit) = localPort.subscribeLoggedUser().map { it != null }
}