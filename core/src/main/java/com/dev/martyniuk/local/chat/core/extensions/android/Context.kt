package com.dev.martyniuk.local.chat.core.extensions.android

import android.content.Context
import android.content.pm.PackageManager

fun Context.isGranted(permission: String) =
    checkSelfPermission(permission) == PackageManager.PERMISSION_GRANTED