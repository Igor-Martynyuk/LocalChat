package com.dev.martyniuk.local.chat.core.layer.data

import com.dev.martyniuk.local.chat.core.layer.domain.auth.CaseSubscribeIsAuthorized
import com.dev.martyniuk.local.chat.core.layer.domain.auth.abstraction.CaseAuthorize
import com.dev.martyniuk.local.chat.core.layer.domain.auth.dto.DtoUserAccount
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton
import kotlin.random.Random

@Singleton
class GatewayDB @Inject constructor() :
    CaseAuthorize.LocalPort,
    CaseSubscribeIsAuthorized.LocalPort {
    private val userFlow = MutableStateFlow<DtoUserAccount?>(null)

    override suspend fun writeUser(user: DtoUserAccount) = flow {
        delay(Random.nextLong(100, 500))
        userFlow.value = user
        emit(Unit)
    }

    override fun subscribeLoggedUser() = userFlow

}