package com.dev.martyniuk.local.chat.core.layer.domain.interactor.auth

import com.dev.martyniuk.local.chat.core.layer.domain.interactor.UseCaseAsync
import com.dev.martyniuk.local.chat.core.layer.domain.interactor.auth.dto.DtoUserAccount
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flatMapConcat
import javax.inject.Inject

@ViewModelScoped
@OptIn(ExperimentalCoroutinesApi::class)
class CaseSignInAsync @Inject constructor(
    private val remotePort: RemotePort,
    private val localPort: CaseAuthAsync.LocalPort
) : UseCaseAsync<CaseSignInAsync.Args, Unit>() {

    data class Args(
        val email: String,
        val password: String
    )

    interface RemotePort {
        suspend fun getAccount(
            email: String,
            password: String
        ): Flow<DtoUserAccount>
    }

    override suspend fun buildFlow(args: Args) = remotePort
        .getAccount(args.email, args.password)
        .flatMapConcat(localPort::writeUser)
}