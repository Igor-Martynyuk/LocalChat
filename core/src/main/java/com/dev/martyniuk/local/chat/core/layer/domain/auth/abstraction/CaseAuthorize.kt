package com.dev.martyniuk.local.chat.core.layer.domain.auth.abstraction

import com.dev.martyniuk.local.chat.core.layer.domain.UseCase
import com.dev.martyniuk.local.chat.core.layer.domain.auth.dto.DtoUserAccount
import kotlinx.coroutines.flow.Flow

abstract class CaseAuthorize<A> : UseCase<A, Flow<Unit>>() {
    interface LocalPort {
        fun writeUser(user: DtoUserAccount): Flow<Unit>
    }
}