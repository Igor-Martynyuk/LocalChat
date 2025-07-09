package com.dev.martyniuk.local.chat.core.di

import com.dev.martyniuk.local.chat.core.layer.data.GatewayDB
import com.dev.martyniuk.local.chat.core.layer.data.GatewayFirebase
import com.dev.martyniuk.local.chat.core.layer.domain.auth.CaseSignInAsync
import com.dev.martyniuk.local.chat.core.layer.domain.auth.CaseSignUpAsync
import com.dev.martyniuk.local.chat.core.layer.domain.auth.CaseSubscribeIsAuthorized
import com.dev.martyniuk.local.chat.core.layer.domain.auth.CaseAuthAsync
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
    fun bindAuthorizeLocalPort(impl: GatewayDB): CaseAuthAsync.LocalPort

    @Binds
    @Singleton
    fun bindSignUpRemotePort(impl: GatewayFirebase): CaseSignUpAsync.RemotePort

    @Binds
    @Singleton
    fun bindLogInRemotePort(impl: GatewayFirebase): CaseSignInAsync.RemotePort

    @Binds
    @Singleton
    fun bindLocalIsAuthorizedLocalPort(impl: GatewayDB): CaseSubscribeIsAuthorized.LocalPort

}