package com.dev.martyniuk.local.chat.ui.view.root.auth.signup.photo

import android.graphics.Bitmap
import androidx.lifecycle.LiveData

interface ContractSignUpPhoto{
    val bitmap: LiveData<Bitmap?>
}