package com.dev.martyniuk.local.chat.core.layer.domain.interactor.auth

import com.dev.martyniuk.local.chat.core.layer.domain.interactor.auth.dto.DtoUserAccount
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flatMapConcat
import javax.inject.Inject

@ViewModelScoped
@OptIn(ExperimentalCoroutinesApi::class)
class CaseSignUpAsync @Inject constructor(
    private val remotePort: RemotePort,
    private val localPort: LocalPort,
) : CaseAuthAsync<CaseSignUpAsync.Args>() {

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

    override suspend fun buildFlow(args: Args) = remotePort
        .createAccount(args.emailAddress, args.password, args.photoUrl)
        .flatMapConcat(localPort::writeUser)

}