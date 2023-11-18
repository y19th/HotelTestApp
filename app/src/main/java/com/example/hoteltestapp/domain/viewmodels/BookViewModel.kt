package com.example.hoteltestapp.domain.viewmodels

import android.telephony.PhoneNumberUtils
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.hoteltestapp.domain.events.BookEvents
import com.example.hoteltestapp.domain.events.InputType
import com.example.hoteltestapp.domain.model.Tourist
import com.example.hoteltestapp.domain.repository.BookRepository
import com.example.hoteltestapp.domain.states.BookState
import com.example.hoteltestapp.util.extension.onlyDigits
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

    private val _data = mutableListOf(Tourist(position = 0))
    val data get() =  _data.toList()

    private val _upState = MutableStateFlow(false)
    val upState = _upState.asStateFlow()

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
        _tourists.update { mapOf(Pair(0,true)) }
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
                    _data.add(Tourist(position = tourists.value.size))
                    map[map.size] = false
                    map.toMap()
                }
            }

            is BookEvents.OnLoseFocus -> {
                when(event.type) {
                    is InputType.Date -> {
                        if(event.text.isEmpty() || event.text.length != 10) {
                            event.onError.invoke()
                        }
                    }
                    is InputType.Email -> {
                        if(!android.util.Patterns.EMAIL_ADDRESS.matcher(event.text).matches()) {
                            event.onError.invoke()
                        }
                    }
                    is InputType.Text -> {
                        if(event.text.isEmpty()) {
                            event.onError.invoke()
                        }
                    }
                    is InputType.Phone -> {
                        val number = event.text.onlyDigits()
                        if(!PhoneNumberUtils.isGlobalPhoneNumber(number) || number.length != 11) {
                            event.onError.invoke()
                        }
                    }
                    is InputType.Pass -> {
                        if(event.text.isEmpty() || event.text.length != 7) {
                            event.onError.invoke()
                        }
                    }
                }
            }
            is BookEvents.OnNameError -> {
                val temp = _data[event.position].copy(isNameError = true)
                _data[event.position] = temp
            }
            is BookEvents.OnSurnameError -> {
                val temp = _data[event.position].copy(isSurnameError = true)
                _data[event.position] = temp
            }
            is BookEvents.OnDateError -> {
                val temp = _data[event.position].copy(isDateError = true)
                _data[event.position] = temp
            }
            is BookEvents.OnCitizenShipError -> {
                val temp = _data[event.position].copy(isCitizenError = true)
                _data[event.position] = temp
            }
            is BookEvents.OnPassNumError -> {
                val temp = _data[event.position].copy(isPassNumError = true)
                _data[event.position] = temp
            }
            is BookEvents.OnPassValidalityError -> {
                val temp = _data[event.position].copy(isPassValidalityError = true)
                _data[event.position] = temp
            }
            is BookEvents.OnEmailChange -> {
                _state.update { it.copy(email = event.email, isEmailError = false) }
            }
            is BookEvents.OnPhoneChange -> {
                _state.update { it.copy(phone = event.phone, isPhoneError = false) }
            }
            is BookEvents.OnNameChange -> {
                val temp = _data[event.position].copy(name = event.name, isNameError = false)
                _data[event.position] = temp
            }
            is BookEvents.OnSurnameChange -> {
                val temp = _data[event.position].copy(surname = event.surname, isSurnameError = false)
                _data[event.position] = temp
            }
            is BookEvents.OnDateChange -> {
                val temp = _data[event.position].copy(date = event.date, isDateError = false)
                _data[event.position] = temp
            }
            is BookEvents.OnCitizenShipChange -> {
                val temp = _data[event.position].copy(citizenShip = event.citizenShip, isCitizenError = false)
                _data[event.position] = temp
            }
            is BookEvents.OnPassNumChange -> {
                val temp = _data[event.position].copy(passNum = event.num, isPassNumError = false)
                _data[event.position] = temp
            }
            is BookEvents.OnPassValidalityChange -> {
                val temp = _data[event.position].copy(passValidality = event.validality, isPassValidalityError = false)
                _data[event.position] = temp
            }
        }
    }

    fun isDataValid(): Boolean {
        var isValid = true
        for((pos, tourist) in data.withIndex()) {
            with(tourist) {
                if(name == "") {
                    isValid = false
                    onEvent(BookEvents.OnNameError(pos))
                }
                if(surname == "") {
                    isValid = false
                    onEvent(BookEvents.OnSurnameError(pos))
                }
                if(date == "" || date.length != 10) {
                    isValid = false
                    onEvent(BookEvents.OnDateError(pos))
                }
                if(citizenShip == "") {
                    isValid = false
                    onEvent(BookEvents.OnCitizenShipError(pos))
                }
                if(passNum == "" || passNum.length != 7) {
                    isValid = false
                    onEvent(BookEvents.OnPassNumError(pos))
                }
                if(passValidality == "" || passValidality.length != 10) {
                    isValid = false
                    onEvent(BookEvents.OnPassValidalityError(pos))
                }
            }
        }
        if(!android.util.Patterns.EMAIL_ADDRESS.matcher(state.value.email).matches()) {
            isValid = false
            _state.update {
                it.copy(isEmailError = true)
            }
        }
        if(!PhoneNumberUtils.isGlobalPhoneNumber(state.value.phone.onlyDigits()) || state.value.phone.onlyDigits().length != 11) {
            isValid = false
            _state.update {
                it.copy(isPhoneError = true)
            }
        }
        _upState.update { !it }

        return isValid
    }

}