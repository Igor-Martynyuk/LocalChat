package com.dev.martyniuk.local.chat.core.layer.data.web

class HttpException(code: Int) : Exception("Request failed with code: $code")