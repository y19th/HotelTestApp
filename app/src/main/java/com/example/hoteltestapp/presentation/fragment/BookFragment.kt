package com.example.hoteltestapp.presentation.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.doOnTextChanged
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.hoteltestapp.R
import com.example.hoteltestapp.databinding.FragmentBookRoomBinding
import com.example.hoteltestapp.domain.events.BookEvents
import com.example.hoteltestapp.domain.model.TourInfo
import com.example.hoteltestapp.domain.viewmodels.BookViewModel
import com.example.hoteltestapp.util.BaseFragment
import com.example.hoteltestapp.util.adapters.TouristTableAdapter
import com.example.hoteltestapp.util.extension.navigateBack
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.getViewModel

class BookFragment: BaseFragment<FragmentBookRoomBinding>(R.layout.fragment_book_room) {

    private val viewModel get() = getViewModel<BookViewModel>()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentBookRoomBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.state.collect {
                    applyUi(it.tourInfo)
                }
            }
        }
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.tourists.collect {
                    applyAdapter(it)
                }
            }
        }

    }

    private fun applyUi(data: TourInfo) {
        binding.apply {

            with(data) {
                this@apply.hotelRating.text = getString(R.string.hotel_rating, hotelRating, ratingDesc)
                hotelTitle.text = hotelName
                hotelLocation.text = hotelAddress
                tourDepartureText.text = departure
                tourCountryText.text = arrivalCountry
                tourDateText.text = getString(R.string.tour_date_text,dateStart,dateStop)
                tourNightsText.text = resources.getQuantityString(R.plurals.nights,nights,nights)
                tourHotelText.text = hotelName
                tourRoomText.text = roomDesc
                tourFoodText.text = food
            }

            backButton.setOnClickListener {
                it.navigateBack()
            }
            phoneEditText.doOnTextChanged { _, start, _, end ->
                when(phoneEditText.text?.length) {
                    1 -> {
                        if (end != 0) phoneEditText.editableText.insert(start,"(")
                    }
                    5 -> {
                        if (end != 0 && start != 0) phoneEditText.editableText.insert(start,") ")
                    }
                    10,13 -> {
                        if (end != 0 && start != 0) phoneEditText.editableText.insert(start,"-")
                    }
                }
            }
            factoryButton.setOnClickListener {
                viewModel.onEvent(BookEvents.OnAddTourist)
            }
        }
    }

    private fun applyAdapter(map: Map<Int,Boolean>) {
        binding.touristRecycler.also {
            it.layoutManager = LinearLayoutManager(requireContext())
        }.adapter = TouristTableAdapter(
            items = map,
            onEvent = viewModel::onEvent
        )
    }

}