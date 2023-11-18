package com.example.hoteltestapp.util.extension

import com.example.hoteltestapp.R
import com.google.android.material.textfield.TextInputLayout

fun TextInputLayout.error(enabled: Boolean) {
    if(enabled) this.error = context.getString(R.string.error_text)
    else this.error = null
}