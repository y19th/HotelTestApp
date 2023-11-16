package com.example.hoteltestapp.util.extension

fun Long.splitPrice(): String {
    var string = ""
    var divider = 1000
    var sDivider = 1
    while(this % divider != this) {
        string = "${this % divider / sDivider} $string"
        divider *= 1000
        sDivider *= 1000
    }
    return "${this % divider / sDivider} $string"
}