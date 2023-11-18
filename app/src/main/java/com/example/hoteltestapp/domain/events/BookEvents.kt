package com.example.hoteltestapp.domain.events


sealed interface BookEvents {

    data object OnAddTourist: BookEvents


    data class OnNameError(val position: Int): BookEvents

    data class OnSurnameError(val position: Int): BookEvents

    data class OnDateError(val position: Int): BookEvents

    data class OnCitizenShipError(val position: Int): BookEvents

    data class OnPassNumError(val position: Int): BookEvents

    data class OnPassValidalityError(val position: Int): BookEvents

    data class OnChangeVisibility(val position: Int) : BookEvents

    data class OnEmailChange(val email: String) : BookEvents

    data class OnPhoneChange(val phone: String): BookEvents

    data class OnLoseFocus(val type: InputType,val text: String, val onError: () -> Unit) : BookEvents

    data class OnNameChange(val position: Int,val name: String) : BookEvents

    data class OnSurnameChange(val position: Int,val surname: String) : BookEvents

    data class OnDateChange(val position: Int,val date: String) : BookEvents

    data class OnCitizenShipChange(val position: Int,val citizenShip: String) : BookEvents

    data class OnPassNumChange(val position: Int,val num: String) : BookEvents

    data class OnPassValidalityChange(val position: Int,val validality: String) : BookEvents
}

sealed interface InputType {
    data object Email : InputType

    data object Date: InputType

    data object Text: InputType

    data object Phone: InputType

    data object Pass: InputType

}