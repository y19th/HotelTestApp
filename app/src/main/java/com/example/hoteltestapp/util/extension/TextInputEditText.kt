package com.example.hoteltestapp.util.extension

import android.view.View
import com.example.hoteltestapp.domain.events.BookEvents
import com.example.hoteltestapp.domain.events.InputType
import com.github.pinball83.maskededittext.MaskedEditText
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout


fun TextInputEditText.onLoseFocus(type: InputType, onLoseFocus: (BookEvents) -> Unit, parent: TextInputLayout) {
    this.onFocusChangeListener = View.OnFocusChangeListener { view, hasFocus ->
        if (!hasFocus) onLoseFocus.invoke(
            BookEvents.OnLoseFocus(
                type = type,
                text = this.text.toString(),
                onError = {
                    parent.error(true)
                }
            ))
    }
}

fun MaskedEditText.onLoseFocus(type: InputType, onLoseFocus: (BookEvents) -> Unit, parent: TextInputLayout) {
    this.onFocusChangeListener = View.OnFocusChangeListener { view, hasFocus ->
        if (!hasFocus) onLoseFocus.invoke(
            BookEvents.OnLoseFocus(
                type = type,
                text = this.text.toString(),
                onError = {
                    parent.error(true)
                }
            ))
        else parent.error(false)
    }
}