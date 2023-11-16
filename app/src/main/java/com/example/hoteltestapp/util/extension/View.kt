package com.example.hoteltestapp.util.extension

import android.view.View

fun View.makeGone() {
    this.visibility = View.GONE
}

fun View.makeVisible() {
    this.visibility = View.VISIBLE
}

fun View.changeVisible(isVisible: Boolean) {
    this.visibility = if(isVisible) View.VISIBLE else View.GONE
}