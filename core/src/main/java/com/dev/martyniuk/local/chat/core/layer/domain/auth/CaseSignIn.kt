package com.dev.martyniuk.local.chat.core.layer.domain.auth

import com.dev.martyniuk.local.chat.core.layer.domain.UseCase
import com.dev.martyniuk.local.chat.core.layer.domain.auth.abstraction.CaseAuthorize
import com.dev.martyniuk.local.chat.core.layer.domain.auth.dto.DtoUserAccount
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flatMapConcat
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
@OptIn(ExperimentalCoroutinesApi::class)
class CaseSignIn @Inject constructor(
    private val remotePort: RemotePort,
    private val localPort: CaseAuthorize.LocalPort
) : UseCase<CaseSignIn.Args, Unit>() {

    data class Args(
        val email: String,
        val password: String
    )

    interface RemotePort {
        fun getAccount(
            email: String,
            password: String
        ): Flow<DtoUserAccount>
    }

    override fun getFlow(args: Args) = remotePort
        .getAccount(args.email, args.password)
        .flatMapConcat(localPort::writeUser)
}