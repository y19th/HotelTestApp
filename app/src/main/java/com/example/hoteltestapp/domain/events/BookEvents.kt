package com.example.hoteltestapp.domain.events

sealed interface BookEvents {

    data object OnAddTourist: BookEvents

    data class OnChangeVisibility(val position: Int) : BookEvents

    data class OnNameChange(val position: Int,val name: String) : BookEvents

    data class OnSurnameChange(val position: Int,val surname: String) : BookEvents

    data class OnDateChange(val position: Int,val date: String) : BookEvents

    data class OnCitizenShipChange(val position: Int,val citizenShip: String) : BookEvents

    data class OnPassNumChange(val position: Int,val num: String) : BookEvents

    data class OnPassValidalityChange(val position: Int,val validality: String) : BookEvents
}