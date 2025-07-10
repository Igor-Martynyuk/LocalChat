package com.dev.martyniuk.local.chat.ui.view.data.binding

import android.graphics.Bitmap
import androidx.databinding.BindingAdapter
import com.canhub.cropper.CropImageView

@BindingAdapter("bitmap")
fun bindBitmap(view: CropImageView, bitmap: Bitmap?) {
    view.setImageBitmap(bitmap)
}