package com.dev.martyniuk.local.chat.core.layer.data

import android.graphics.BitmapFactory
import android.net.Uri
import androidx.core.net.toFile
import com.dev.martyniuk.local.chat.core.layer.domain.interactor.img.CaseLoadBitmap
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GatewayFileSystem @Inject constructor() : CaseLoadBitmap.PortIn {
    override suspend fun loadBitmap(uri: Uri) = flow {
        val file = uri.toFile()

        if (file.exists()) emit(BitmapFactory.decodeFile(file.absolutePath))
        else throw FileSystemException(file = file, reason = "File not found")
    }
}