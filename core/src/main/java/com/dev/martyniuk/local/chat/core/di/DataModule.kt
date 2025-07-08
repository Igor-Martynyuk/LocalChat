package com.dev.martyniuk.local.chat.core.di

import com.dev.martyniuk.local.chat.core.layer.data.GatewayDB
import com.dev.martyniuk.local.chat.core.layer.data.GatewayFirebase
import com.dev.martyniuk.local.chat.core.layer.domain.auth.CaseSignIn
import com.dev.martyniuk.local.chat.core.layer.domain.auth.CaseSignUp
import com.dev.martyniuk.local.chat.core.layer.domain.auth.CaseSubscribeIsAuthorized
import com.dev.martyniuk.local.chat.core.layer.domain.auth.abstraction.CaseAuth
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface DataModule {

    @Binds
    @Singleton
    fun bindAuthorizeLocalPort(impl: GatewayDB): CaseAuth.LocalPort

    @Binds
    @Singleton
    fun bindSignUpRemotePort(impl: GatewayFirebase): CaseSignUp.RemotePort

    @Binds
    @Singleton
    fun bindLogInRemotePort(impl: GatewayFirebase): CaseSignIn.RemotePort

    @Binds
    @Singleton
    fun bindLocalIsAuthorizedLocalPort(impl: GatewayDB): CaseSubscribeIsAuthorized.LocalPort

}