package com.dev.martyniuk.local.chat.core.layer.data.web.common

class HttpException(code: Int) : Exception("Request failed with code: $code")