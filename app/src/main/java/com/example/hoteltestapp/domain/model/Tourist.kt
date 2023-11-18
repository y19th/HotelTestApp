package com.example.hoteltestapp.domain.model

data class Tourist(
    val position: Int = -1,

    val name: String = "",
    val surname: String = "",
    val date: String = "",
    val citizenShip: String = "",
    val passNum: String = "",
    val passValidality: String = "",

    val isNameError: Boolean = false,
    val isSurnameError: Boolean = false,
    val isDateError: Boolean = false,
    val isCitizenError: Boolean = false,
    val isPassNumError: Boolean = false,
    val isPassValidalityError: Boolean = false
)
