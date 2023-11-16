package com.example.hoteltestapp.util.adapters

import android.graphics.Bitmap
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.hoteltestapp.presentation.fragment.HotelPhotosFragment

class HotelPhotosAdapter(
    fragment: FragmentActivity,
    private val items: List<String>
)  : FragmentStateAdapter(fragment) {
    override fun getItemCount(): Int = items.size

    override fun createFragment(
        position: Int
    ): Fragment = HotelPhotosFragment(
        item = items[position],
        position = position,
        itemCount = itemCount
    )

}