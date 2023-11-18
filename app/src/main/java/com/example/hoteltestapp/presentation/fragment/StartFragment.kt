package com.example.hoteltestapp.presentation.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.findNavController
import com.example.hoteltestapp.R
import com.example.hoteltestapp.util.adapters.HotelPhotosAdapter
import com.example.hoteltestapp.util.adapters.HotelPinAdapter
import com.example.hoteltestapp.databinding.FragmentStartPointBinding
import com.example.hoteltestapp.domain.model.HotelInfo
import com.example.hoteltestapp.domain.viewmodels.HotelViewModel
import com.example.hoteltestapp.util.extension.navigate
import com.example.hoteltestapp.util.extension.splitPrice
import com.google.android.flexbox.FlexDirection
import com.google.android.flexbox.FlexWrap
import com.google.android.flexbox.FlexboxLayoutManager
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.getViewModel

class StartFragment() : Fragment(R.layout.fragment_start_point) {

    private var _binding: FragmentStartPointBinding? = null
    private val binding: FragmentStartPointBinding get() = requireNotNull(_binding)

    private val viewModel get() = getViewModel<HotelViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentStartPointBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.state.collect {
                    applyUi(it.hotelInfo)
                }
            }
        }

    }
    private fun applyUi(data: HotelInfo) {
        binding.apply {
            with(data) {
                hotelPager.adapter = HotelPhotosAdapter(
                    this@StartFragment.requireActivity(),
                    imageUrls
                )
                hotelRating.text = getString(R.string.hotel_rating, rating, ratingDesc)
                hotelTitle.text = name
                hotelLocation.text = adress
                hotelPrice.text = getString(R.string.price, price.splitPrice())
                hotelPriceDesc.text = priceDesc
                this@apply.hotelDesc.text = hotelDesc.description

                hotelPinsRecycler.also {
                    it.layoutManager = FlexboxLayoutManager(requireContext()).also { manager ->
                        manager.flexWrap = FlexWrap.WRAP
                        manager.flexDirection = FlexDirection.ROW
                    }
                }.adapter = HotelPinAdapter(hotelDesc.pins)
            }
            nextButton.setOnClickListener {
                it.navigate(StartFragmentDirections.actionStartFragmentToHotelDescriptionFragment(data.name))
            }
        }
    }
}