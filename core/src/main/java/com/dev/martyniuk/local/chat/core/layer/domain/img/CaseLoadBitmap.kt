package com.dev.martyniuk.local.chat.core.layer.domain.img

import android.graphics.Bitmap
import android.net.Uri
import com.dev.martyniuk.local.chat.core.di.SourceFS
import com.dev.martyniuk.local.chat.core.di.SourceOS
import com.dev.martyniuk.local.chat.core.di.SourceRemote
import com.dev.martyniuk.local.chat.core.extensions.android.isContent
import com.dev.martyniuk.local.chat.core.extensions.android.isFTP
import com.dev.martyniuk.local.chat.core.extensions.android.isFile
import com.dev.martyniuk.local.chat.core.extensions.android.isHttp
import com.dev.martyniuk.local.chat.core.extensions.android.isHttps
import com.dev.martyniuk.local.chat.core.layer.domain.abstraction.UseCaseAsync
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@ViewModelScoped
class CaseLoadBitmap @Inject constructor(
    @SourceFS private val fs: PortIn,
    @SourceOS private val os: PortIn,
    @SourceRemote private val remote: PortIn,
) : UseCaseAsync<Uri, Bitmap>() {
    interface PortIn {
        suspend fun loadBitmap(uri: Uri): Flow<Bitmap>
    }

    override suspend fun buildFlow(args: Uri) = when {
        args.isHttp() || args.isHttps() || args.isFTP() -> remote.loadBitmap(args)
        args.isContent() -> os.loadBitmap(args)
        args.isFile() -> fs.loadBitmap(args)
        else -> throw UnsupportedOperationException(
            "Invalid uri received: $args\nonly file:// and http://, https://, htp:// uri is supported"
        )
    }
}