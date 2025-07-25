package com.dev.martyniuk.local.chat.core.layer.domain.img

import android.graphics.Bitmap
import android.net.Uri
import com.dev.martyniuk.local.chat.core.di.Local
import com.dev.martyniuk.local.chat.core.di.Remote
import com.dev.martyniuk.local.chat.core.extensions.isFile
import com.dev.martyniuk.local.chat.core.extensions.isWeb
import com.dev.martyniuk.local.chat.core.layer.domain.abstraction.UseCaseAsync
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@ViewModelScoped
class CaseLoadBitmap @Inject constructor(
    @Local private val localInPort: PortIn,
    @Remote private val remoteInPort: PortIn,
) : UseCaseAsync<Uri, Bitmap>() {
    interface PortIn {
        suspend fun readBitmap(uri: Uri): Flow<Bitmap>
    }

    override suspend fun buildFlow(args: Uri) = when {
        args.isFile() -> localInPort.readBitmap(args)
        args.isWeb() -> remoteInPort.readBitmap(args)
        else -> throw UnsupportedOperationException("only file:// and http://, https://, htp:// uri is supported")
    }
}