package com.example.hoteltestapp.util.extension

import android.view.View
import androidx.navigation.NavDirections
import androidx.navigation.findNavController

fun View.navigate(navDirections: NavDirections) {
    this.findNavController().navigate(navDirections)
}