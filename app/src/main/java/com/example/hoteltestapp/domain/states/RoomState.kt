package com.example.hoteltestapp.domain.states

import com.example.hoteltestapp.domain.model.RoomModel

data class RoomState(
    val rooms: List<RoomModel> = emptyList()
)
