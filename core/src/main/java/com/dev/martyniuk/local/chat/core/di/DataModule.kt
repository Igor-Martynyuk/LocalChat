package com.dev.martyniuk.local.chat.core.di

import com.dev.martyniuk.local.chat.core.layer.data.GatewayAndroid
import com.dev.martyniuk.local.chat.core.layer.data.GatewayFileSystem
import com.dev.martyniuk.local.chat.core.layer.data.GatewayFirebase
import com.dev.martyniuk.local.chat.core.layer.data.web.GatewayWeb
import com.dev.martyniuk.local.chat.core.layer.domain.auth.CaseSignInAsync
import com.dev.martyniuk.local.chat.core.layer.domain.auth.CaseSignUpAsync
import com.dev.martyniuk.local.chat.core.layer.domain.auth.CaseSubscribeIsAuthorized
import com.dev.martyniuk.local.chat.core.layer.domain.auth.CaseAuthAsync
import com.dev.martyniuk.local.chat.core.layer.domain.img.CaseLoadBitmap
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
    fun bindAuthorizeLocalPort(impl: GatewayAndroid): CaseAuthAsync.LocalPort

    @Binds
    @Singleton
    fun bindSignUpRemotePort(impl: GatewayFirebase): CaseSignUpAsync.RemotePort

    @Binds
    @Singleton
    fun bindLogInRemotePort(impl: GatewayFirebase): CaseSignInAsync.RemotePort

    @Binds
    @Singleton
    fun bindLocalIsAuthorizedLocalPort(impl: GatewayAndroid): CaseSubscribeIsAuthorized.LocalPort

    @Binds
    @Singleton
    @SourceRemote
    fun bindRemoteImgInPort(impl: GatewayWeb): CaseLoadBitmap.PortIn

    @Binds
    @Singleton
    @SourceOS
    fun bindOsImgInPort(impl: GatewayAndroid): CaseLoadBitmap.PortIn

    @Binds
    @Singleton
    @SourceFS
    fun bindFSImgInPort(impl: GatewayFileSystem): CaseLoadBitmap.PortIn
}