package com.example.hoteltestapp.domain.viewmodels

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.hoteltestapp.data.model.toRoomList
import com.example.hoteltestapp.domain.repository.RoomRepository
import com.example.hoteltestapp.domain.states.RoomState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class RoomViewModel: ViewModel() {

    companion object {
        const val TAG = "RoomViewModel"
    }

    private val _state = MutableStateFlow(RoomState())
    val state = _state.asStateFlow()

    private val repository = RoomRepository()


    init {
        viewModelScope.launch(Dispatchers.IO) {
            repository.fetchRooms(
                onResult = { rooms ->
                    Log.i(TAG,"response is $rooms")

                    if(rooms != null) {
                        _state.update {
                            it.copy(rooms = rooms.rooms.toRoomList())
                        }
                    }
                },
                onError = {
                    Log.i(TAG,"throwed ${it.message}")
                }
            )
        }
    }

}