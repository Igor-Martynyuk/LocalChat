package com.dev.martyniuk.local.chat.core.layer.data

import android.content.Context
import android.graphics.BitmapFactory
import android.net.Uri
import com.dev.martyniuk.local.chat.core.extensions.kotlin.isNull
import com.dev.martyniuk.local.chat.core.layer.domain.interactor.auth.CaseAuthAsync
import com.dev.martyniuk.local.chat.core.layer.domain.interactor.auth.CaseSubscribeIsAuthorized
import com.dev.martyniuk.local.chat.core.layer.domain.interactor.auth.dto.DtoUserAccount
import com.dev.martyniuk.local.chat.core.layer.domain.interactor.img.CaseLoadBitmap
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import javax.inject.Singleton
import kotlin.random.Random

@Singleton
class GatewayOperatingSystem @Inject constructor(
    @ApplicationContext private val context: Context
) : CaseAuthAsync.LocalPort, CaseSubscribeIsAuthorized.LocalPort, CaseLoadBitmap.PortIn {
    private val userFlow = MutableStateFlow<DtoUserAccount?>(null)

    override suspend fun writeUser(user: DtoUserAccount) = flow {
        delay(Random.nextLong(100, 500))
        userFlow.value = user
        emit(Unit)
    }

    override fun subscribeLoggedUser() = userFlow

    @Suppress("BlockingMethodInNonBlockingContext")
    override suspend fun loadBitmap(uri: Uri) = flow {
        val input = context.contentResolver.openInputStream(uri)

        if (input.isNull()) throw IllegalStateException("File not found by uri: $uri")
        else emit(BitmapFactory.decodeStream(input))

        input?.close()
    }

}