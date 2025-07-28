package com.dev.martyniuk.local.chat.core.layer.data.web.firebase

import com.dev.martyniuk.local.chat.core.layer.domain.interactor.auth.CaseSignInAsync
import com.dev.martyniuk.local.chat.core.layer.domain.interactor.auth.CaseSignUpAsync
import com.dev.martyniuk.local.chat.core.layer.domain.interactor.auth.dto.DtoUserAccount
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import javax.inject.Singleton
import kotlin.random.Random

@Singleton
class GatewayFirebase @Inject constructor() : CaseSignUpAsync.RemotePort, CaseSignInAsync.RemotePort {

    override suspend fun createAccount(email: String, password: String, photoUrl: String) =
        flow {
            delay(Random.nextLong(1000, 3000))
            emit(
                DtoUserAccount(
                    "0",
                    email,
                    "Default",
                    photoUrl
                )
            )
        }

    override suspend fun getAccount(email: String, password: String) = flow {
        delay(Random.nextLong(1000, 3000))
        emit(
            DtoUserAccount(
                "0",
                email,
                "Default",
                "http::/localhost"
            )
        )
    }

}