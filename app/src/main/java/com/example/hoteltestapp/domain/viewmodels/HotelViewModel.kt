package com.example.hoteltestapp.domain.viewmodels

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.hoteltestapp.domain.repository.HotelRepository
import com.example.hoteltestapp.domain.states.HotelState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class HotelViewModel: ViewModel() {

    companion object {
        const val TAG = "HotelViewModel"
    }

    private val _state = MutableStateFlow(HotelState())
    val state = _state.asStateFlow()

    private val repository = HotelRepository()

    init {
        viewModelScope.launch(Dispatchers.IO) {
            repository.fetchHotelInfo(
                onResult = { info ->
                    if(info != null) {
                        _state.update {
                            it.copy(hotelInfo = info.toHotelInfo())
                        }
                    } else Log.i(TAG,"response is $info")
                },
                onError = {
                    Log.i(TAG,"throwed ${it.message}")
                }
            )
        }
    }


}