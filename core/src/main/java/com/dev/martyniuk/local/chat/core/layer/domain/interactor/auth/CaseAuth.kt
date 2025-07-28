package com.dev.martyniuk.local.chat.core.layer.domain.interactor.auth

import com.dev.martyniuk.local.chat.core.layer.domain.interactor.UseCaseAsync
import com.dev.martyniuk.local.chat.core.layer.domain.interactor.auth.dto.DtoUserAccount
import kotlinx.coroutines.flow.Flow

abstract class CaseAuthAsync<A> : UseCaseAsync<A, Unit>() {
    interface LocalPort {
        suspend fun writeUser(user: DtoUserAccount): Flow<Unit>
    }
}