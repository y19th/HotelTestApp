package com.example.hoteltestapp.presentation.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.navArgs
import com.example.hoteltestapp.R
import com.example.hoteltestapp.databinding.FragmentHotelDescriptionBinding
import com.example.hoteltestapp.domain.model.RoomModel
import com.example.hoteltestapp.domain.viewmodels.RoomViewModel
import com.example.hoteltestapp.util.adapters.HotelPhotosAdapter
import com.example.hoteltestapp.util.adapters.HotelPinAdapter
import com.example.hoteltestapp.util.extension.splitPrice
import com.google.android.flexbox.FlexDirection
import com.google.android.flexbox.FlexWrap
import com.google.android.flexbox.FlexboxLayoutManager
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.getViewModel

class HotelDescriptionFragment: Fragment(R.layout.fragment_hotel_description) {

    private var _binding: FragmentHotelDescriptionBinding? = null
    private val binding get() = requireNotNull(_binding)

    private val args: HotelDescriptionFragmentArgs by navArgs()

    private val viewModel get() = getViewModel<RoomViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHotelDescriptionBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.state.collect {
                    if(it.rooms.isNotEmpty())applyUi(it.rooms[0])
                }
            }
        }
    }

    private fun applyUi(data: RoomModel) {
        binding.apply {
            topBarText.text = args.hotelName
            with(data) {
                roomPager.adapter = HotelPhotosAdapter(
                    this@HotelDescriptionFragment.requireActivity(),
                    imageUrls
                )
                roomName.text = name
                roomPrice.text = price.splitPrice()
                roomPriceDesc.text = pricePer
                roomPinsRecycler.also {
                    it.layoutManager = FlexboxLayoutManager(requireContext()).also { manager ->
                        manager.flexWrap = FlexWrap.WRAP
                        manager.flexDirection = FlexDirection.ROW
                    }
                }.adapter = HotelPinAdapter(pins)
            }

        }
    }
}