package com.dev.martyniuk.local.chat.core.exception

class HttpException(code: Int) : Exception("Request failed with code: $code")