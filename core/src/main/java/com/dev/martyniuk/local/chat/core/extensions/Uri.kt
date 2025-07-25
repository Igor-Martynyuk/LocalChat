package com.dev.martyniuk.local.chat.core.extensions

import android.net.Uri

fun Uri.isWeb() = (scheme == "http")
    .or(scheme == "https")
    .or(scheme == "ftp")

fun Uri.isFile() = scheme == "file"