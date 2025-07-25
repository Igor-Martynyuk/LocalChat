package com.dev.martyniuk.local.chat.core.layer.domain.abstraction

import kotlinx.coroutines.flow.Flow

abstract class UseCaseAsync<in A, out R> {
    abstract suspend fun buildFlow(args: A): Flow<R>
}