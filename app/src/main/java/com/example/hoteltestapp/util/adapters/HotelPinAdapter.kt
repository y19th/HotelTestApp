package com.example.hoteltestapp.util.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.hoteltestapp.databinding.ItemHotelPinBinding

class HotelPinAdapter(
    private val items: List<String>
): BaseAdapter<ItemHotelPinBinding>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): BaseViewHolder<ItemHotelPinBinding> {
        _binding = ItemHotelPinBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return BaseViewHolder(binding)
    }


    override fun onBindViewHolder(holder: BaseViewHolder<ItemHotelPinBinding>, position: Int) {
        binding.pinText.text = items[position]
    }

    override fun getItemCount(): Int = items.size
}