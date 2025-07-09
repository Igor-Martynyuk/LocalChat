package com.dev.martyniuk.local.chat.core.layer.domain

import kotlinx.coroutines.flow.Flow

abstract class UseCaseAsync<in A, out R> {
    abstract suspend fun flow(args: A): Flow<R>
}