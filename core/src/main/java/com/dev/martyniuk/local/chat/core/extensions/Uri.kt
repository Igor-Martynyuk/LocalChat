package com.dev.martyniuk.local.chat.core.extensions

import android.net.Uri


fun Uri.isHttp() = this.scheme == "http"
fun Uri.isHttps() = this.scheme == "https"
fun Uri.isFTP() = this.scheme == "ftp"
fun Uri.isContent() = this.scheme == "content"
fun Uri.isFile() = this.scheme == "file"