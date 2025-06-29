package com.dev.martyniuk.local.chat.core.domain

import kotlinx.coroutines.flow.Flow


abstract class UseCase<A, R> {
    abstract suspend fun getFlow(args: A): Flow<R>
}