package com.dev.martyniuk.local.chat.core.domain.auth

import com.dev.martyniuk.local.chat.core.domain.UseCase
import com.dev.martyniuk.local.chat.core.domain.auth.dto.DtoUserAccount
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CaseSubscribeIsAuthorized @Inject constructor(
    private val localPort: LocalPort
) : UseCase<Unit, Boolean>() {
    interface LocalPort {
        fun subscribeLoggedUser(): Flow<DtoUserAccount?>
    }

    override suspend fun getFlow(args: Unit) = localPort.subscribeLoggedUser().map { it != null }
}