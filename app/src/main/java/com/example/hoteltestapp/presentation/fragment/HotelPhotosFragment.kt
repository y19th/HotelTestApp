package com.example.hoteltestapp.presentation.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.hoteltestapp.R
import com.example.hoteltestapp.util.adapters.HotelTabAdapter
import com.example.hoteltestapp.databinding.ItemHotelPhotosBinding
import java.net.URL

class HotelPhotosFragment(
    private val item: String,
    private val position: Int,
    private val itemCount: Int
) : Fragment(R.layout.item_hotel_photos) {

    private var _binding: ItemHotelPhotosBinding? = null
    private val binding: ItemHotelPhotosBinding get() = requireNotNull(_binding)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = ItemHotelPhotosBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.apply {
            Glide.with(requireContext()).load(item).into(hotelPhoto)
            tabRecyclerView.also {
                it.layoutManager = LinearLayoutManager(requireContext(),LinearLayoutManager.HORIZONTAL,false)
            }.adapter = HotelTabAdapter(getTabsList())
        }
    }


    private fun getTabsList() : List<Boolean> {
        val list = mutableListOf<Boolean>()
        (0 until itemCount).forEach {
            list.add(it == position)
        }
        return list
    }
}