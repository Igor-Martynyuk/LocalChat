package com.dev.martyniuk.local.chat.core.layer.data.web.common

import android.graphics.BitmapFactory
import android.net.Uri
import com.dev.martyniuk.local.chat.core.layer.domain.interactor.img.CaseLoadBitmap
import kotlinx.coroutines.flow.flow
import okhttp3.OkHttpClient
import okhttp3.Request
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GatewayWebCommon @Inject constructor() : CaseLoadBitmap.PortIn {
    private val client = OkHttpClient()

    override suspend fun loadBitmap(uri: Uri) = flow {
        val response = client.newCall(
            Request.Builder()
                .url(uri.toString())
                .build()
        ).execute()

        if (response.isSuccessful) emit(response.body.bytes().let { BitmapFactory.decodeByteArray(it, 0, it.size) })
        else throw HttpException(response.code)
        response.close()
    }
}