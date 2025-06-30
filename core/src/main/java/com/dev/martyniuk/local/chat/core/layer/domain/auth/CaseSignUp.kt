package com.dev.martyniuk.local.chat.core.layer.domain.auth

import com.dev.martyniuk.local.chat.core.layer.domain.auth.dto.DtoUserAccount
import com.dev.martyniuk.local.chat.core.layer.domain.auth.abstraction.CaseAuthorize
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flatMapConcat
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
@OptIn(ExperimentalCoroutinesApi::class)
class CaseSignUp @Inject constructor(
    private val remotePort: RemotePort,
    private val localPort: LocalPort,
) : CaseAuthorize<CaseSignUp.Args>() {

    interface RemotePort {
        suspend fun createAccount(
            email: String,
            password: String,
            photoUrl: String
        ): Flow<DtoUserAccount>
    }

    data class Args(
        val emailAddress: String,
        val password: String,
        val photoUrl: String
    )

    override suspend fun getFlow(args: Args) = remotePort
        .createAccount(args.emailAddress, args.password, args.photoUrl)
        .flatMapConcat(localPort::writeUser)

}