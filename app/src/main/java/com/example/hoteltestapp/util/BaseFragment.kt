package com.example.hoteltestapp.util

import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding

@Suppress("PropertyName")
open class BaseFragment<T: ViewBinding>(@LayoutRes layout: Int): Fragment(layout) {

    var _binding: T? = null
    val binding: T get() = requireNotNull(_binding)


    override fun onDestroy() {
        _binding = null
        super.onDestroy()
    }
}