package com.dev.martyniuk.local.chat.core.di

import com.dev.martyniuk.local.chat.core.layer.data.GatewayOperatingSystem
import com.dev.martyniuk.local.chat.core.layer.data.GatewayFileSystem
import com.dev.martyniuk.local.chat.core.layer.data.web.firebase.GatewayFirebase
import com.dev.martyniuk.local.chat.core.layer.data.web.common.GatewayWebCommon
import com.dev.martyniuk.local.chat.core.layer.domain.interactor.auth.CaseSignInAsync
import com.dev.martyniuk.local.chat.core.layer.domain.interactor.auth.CaseSignUpAsync
import com.dev.martyniuk.local.chat.core.layer.domain.interactor.auth.CaseSubscribeIsAuthorized
import com.dev.martyniuk.local.chat.core.layer.domain.interactor.auth.CaseAuthAsync
import com.dev.martyniuk.local.chat.core.layer.domain.interactor.img.CaseLoadBitmap
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
    fun bindAuthorizeLocalPort(impl: GatewayOperatingSystem): CaseAuthAsync.LocalPort

    @Binds
    @Singleton
    fun bindSignUpRemotePort(impl: GatewayFirebase): CaseSignUpAsync.RemotePort

    @Binds
    @Singleton
    fun bindLogInRemotePort(impl: GatewayFirebase): CaseSignInAsync.RemotePort

    @Binds
    @Singleton
    fun bindLocalIsAuthorizedLocalPort(impl: GatewayOperatingSystem): CaseSubscribeIsAuthorized.LocalPort

    @Binds
    @Singleton
    @SourceRemote
    fun bindRemoteImgInPort(impl: GatewayWebCommon): CaseLoadBitmap.PortIn

    @Binds
    @Singleton
    @SourceOS
    fun bindOsImgInPort(impl: GatewayOperatingSystem): CaseLoadBitmap.PortIn

    @Binds
    @Singleton
    @SourceFS
    fun bindFSImgInPort(impl: GatewayFileSystem): CaseLoadBitmap.PortIn
}