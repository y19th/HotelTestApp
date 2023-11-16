package com.example.hoteltestapp.util.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.FragmentActivity
import com.example.hoteltestapp.databinding.ItemBookRoomBinding
import com.example.hoteltestapp.domain.model.RoomModel
import com.example.hoteltestapp.presentation.fragment.HotelDescriptionFragmentDirections
import com.example.hoteltestapp.util.extension.navigate
import com.example.hoteltestapp.util.extension.splitPrice
import com.google.android.flexbox.FlexboxLayoutManager
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class BookRoomAdapter(
    private val rooms: List<RoomModel>,
    private val activity: FragmentActivity
): BaseAdapter<ItemBookRoomBinding>(), KoinComponent {

    private val context: Context by inject()

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): BaseViewHolder<ItemBookRoomBinding> {
        _binding = ItemBookRoomBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return BaseViewHolder(binding)
    }

    override fun onBindViewHolder(holder: BaseViewHolder<ItemBookRoomBinding>, position: Int) {
        binding.apply {
            with(rooms[position]) {
                roomPager.adapter = HotelPhotosAdapter(
                    activity,
                    imageUrls
                )
                roomName.text = name
                roomPrice.text = price.splitPrice()
                roomPriceDesc.text = pricePer
                roomPinsRecycler.also {
                    it.layoutManager = FlexboxLayoutManager(context).also { manager ->
                        manager.flexWrap = com.google.android.flexbox.FlexWrap.WRAP
                        manager.flexDirection = com.google.android.flexbox.FlexDirection.ROW
                    }
                }.adapter = HotelPinAdapter(pins)
                nextButton.setOnClickListener {
                    it.navigate(HotelDescriptionFragmentDirections.actionHotelDescriptionFragmentToBookFragment())
                }
            }

        }
    }


    override fun getItemCount(): Int = rooms.size
}