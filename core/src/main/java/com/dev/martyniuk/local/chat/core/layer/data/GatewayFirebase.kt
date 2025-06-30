package com.dev.martyniuk.local.chat.core.layer.data

import com.dev.martyniuk.local.chat.core.layer.domain.auth.CaseLogIn
import com.dev.martyniuk.local.chat.core.layer.domain.auth.CaseSignUp
import com.dev.martyniuk.local.chat.core.layer.domain.auth.dto.DtoUserAccount
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import javax.inject.Singleton
import kotlin.random.Random

@Singleton
class GatewayFirebase @Inject constructor() : CaseSignUp.RemotePort, CaseLogIn.RemotePort {

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

    override fun getAccount(email: String, password: String) = flow {
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