package com.example.hoteltestapp.util.adapters

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.hoteltestapp.databinding.ItemHotelTabBinding

class HotelTabAdapter(
  private val items: List<Boolean>
) : BaseAdapter<ItemHotelTabBinding>() {

    companion object {
        val CHOSEN_COLOR = Color.argb(255,0,0,0)
        val UNCHOSEN_COLOR = Color.argb(50,0,0,0)
    }


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): BaseViewHolder<ItemHotelTabBinding> {
        _binding = ItemHotelTabBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return BaseViewHolder(binding)
    }

    override fun onBindViewHolder(holder: BaseViewHolder<ItemHotelTabBinding>, position: Int) {
        when(items[position]) {
            true -> {
                binding.itemTab.setColorFilter(CHOSEN_COLOR)
            }
            false -> {
                binding.itemTab.setColorFilter(UNCHOSEN_COLOR)
            }
        }
    }

    override fun getItemCount(): Int = items.size
}