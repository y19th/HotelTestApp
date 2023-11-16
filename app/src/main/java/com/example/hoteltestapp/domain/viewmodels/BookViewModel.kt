package com.example.hoteltestapp.domain.viewmodels

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.hoteltestapp.domain.events.BookEvents
import com.example.hoteltestapp.domain.model.Tourist
import com.example.hoteltestapp.domain.repository.BookRepository
import com.example.hoteltestapp.domain.states.BookState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class BookViewModel : ViewModel() {

    companion object {
        const val TAG = "BookViewModel"
    }

    private val _state = MutableStateFlow(BookState())
    val state = _state.asStateFlow()

    private val _tourists = MutableStateFlow(mapOf<Int,Boolean>())
    val tourists = _tourists.asStateFlow()

    private val _data = mutableListOf(Tourist(position = 0), Tourist(position = 1))
    val data get() =  _data.toList()

    private val repository = BookRepository()

    init {
        viewModelScope.launch(Dispatchers.IO) {
            repository.fetchTourInfo(
                onResult = { response ->
                    Log.i(TAG,"response is $response")
                    if(response != null) {
                        _state.update {
                            it.copy(tourInfo = response.toTourInfo())
                        }
                    }
                },
                onError = {
                    Log.i(TAG,"throwed ${it.message}")
                }
            )
        }
        _tourists.update { mapOf(Pair(0,true),Pair(1,false)) }
    }

    fun onEvent(event: BookEvents) {
        when(event) {
            is BookEvents.OnChangeVisibility -> {
                _tourists.update {
                    val map = it.toMutableMap()
                    if(map[event.position] != null) {
                        map[event.position] = !map[event.position]!!
                    }
                    map.toMap()
                }
                Log.i("debugmode",_tourists.value.toString())
            }
            is BookEvents.OnAddTourist -> {
                _tourists.update {
                    val map = it.toMutableMap()
                    map[map.size] = false
                    map.toMap()
                }

            }
            is BookEvents.OnNameChange -> {
                val temp = _data[event.position].copy(name = event.name)
                _data[event.position] = temp
            }
            is BookEvents.OnSurnameChange -> {
                val temp = _data[event.position].copy(surname = event.surname)
                _data[event.position] = temp
            }
            is BookEvents.OnDateChange -> {
                val temp = _data[event.position].copy(date = event.date)
                _data[event.position] = temp
            }
            is BookEvents.OnCitizenShipChange -> {
                val temp = _data[event.position].copy(citizenShip = event.citizenShip)
                _data[event.position] = temp
            }
            is BookEvents.OnPassNumChange -> {
                val temp = _data[event.position].copy(passNum = event.num)
                _data[event.position] = temp
            }
            is BookEvents.OnPassValidalityChange -> {
                val temp = _data[event.position].copy(passValidality = event.validality)
                _data[event.position] = temp
            }
        }
    }

}