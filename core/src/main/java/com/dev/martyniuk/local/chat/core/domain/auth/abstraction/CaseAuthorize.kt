package com.dev.martyniuk.local.chat.core.domain.auth.abstraction

import com.dev.martyniuk.local.chat.core.domain.UseCase
import com.dev.martyniuk.local.chat.core.domain.auth.dto.DtoUserAccount
import kotlinx.coroutines.flow.Flow

abstract class CaseAuthorize<A> : UseCase<A, Unit>() {
    interface LocalPort {
        suspend fun writeUser(
            user: DtoUserAccount
        ): Flow<Unit>
    }
}