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
import com.example.hoteltestapp.domain.events.InputType
import com.example.hoteltestapp.domain.model.TourInfo
import com.example.hoteltestapp.domain.viewmodels.BookViewModel
import com.example.hoteltestapp.util.BaseFragment
import com.example.hoteltestapp.util.adapters.TouristTableAdapter
import com.example.hoteltestapp.util.extension.error
import com.example.hoteltestapp.util.extension.navigate
import com.example.hoteltestapp.util.extension.navigateBack
import com.example.hoteltestapp.util.extension.onLoseFocus
import com.example.hoteltestapp.util.extension.splitPrice
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
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.upState.collect {
                    applyAdapter(viewModel.tourists.value)
                }
            }
        }

    }

    private fun applyUi(data: TourInfo) {
        binding.apply {
            with(data) {
                val fullPrice = tourPrice + fuelCharge + serviceCharge

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
                priceTourPrice.text = requireContext().getString(R.string.room_price,tourPrice.splitPrice())
                priceFuelPrice.text = requireContext().getString(R.string.room_price,fuelCharge.splitPrice())
                priceServicePrice.text = requireContext().getString(R.string.room_price,serviceCharge.splitPrice())
                priceAllPrice.text = requireContext().getString(R.string.room_price,fullPrice.splitPrice())
                nextButton.text = requireContext().getString(R.string.pay_all, priceAllPrice.text)
            }

            nextButton.setOnClickListener {
                if(viewModel.isDataValid()) {
                    it.navigate(BookFragmentDirections.actionBookFragmentToOrderFragment())
                }
            }

            backButton.setOnClickListener {
                it.navigateBack()
            }

            phoneEditText.also {
                it.doOnTextChanged { _, _, _, _ ->
                    if(!phoneEditText.text.isNullOrEmpty())viewModel.onEvent(BookEvents.OnPhoneChange(phoneEditText.text.toString()))
                    phoneLayout.error(false)
                }
                phoneLayout.error(viewModel.state.value.isPhoneError)
            }.onLoseFocus(type = InputType.Phone, onLoseFocus = viewModel::onEvent,parent = phoneLayout)

            emailEditText.also {
                it.doOnTextChanged { _,_,_,_ ->
                    viewModel.onEvent(BookEvents.OnEmailChange(emailEditText.text.toString()))
                    emailLayout.error(false)
                }
                emailLayout.error(viewModel.state.value.isEmailError)
            }.onLoseFocus(type = InputType.Email, onLoseFocus = viewModel::onEvent, parent = emailLayout)

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
            onEvent = viewModel::onEvent,
            previousData = viewModel.data,
            viewLifecycleOwner = viewLifecycleOwner
        )
    }

}