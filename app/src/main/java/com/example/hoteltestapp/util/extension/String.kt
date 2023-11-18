package com.example.hoteltestapp.util.extension

import java.util.regex.Pattern

fun String.onlyDigits(): String {
    val builder = StringBuilder()
    val pattern = Pattern.compile("[0-9]")
    for(char in this) {
        if(pattern.matcher(char.toString()).matches()) builder.append(char)
    }
    return builder.toString()
}