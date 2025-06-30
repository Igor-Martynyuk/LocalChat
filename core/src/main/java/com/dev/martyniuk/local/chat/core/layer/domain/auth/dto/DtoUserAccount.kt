package com.dev.martyniuk.local.chat.core.layer.domain.auth.dto


data class DtoUserAccount(
    val id: String,
    val emailAddress: String,
    val displayName: String,
    val photoUrl: String
)