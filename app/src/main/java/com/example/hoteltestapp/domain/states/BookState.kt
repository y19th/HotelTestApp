package com.example.hoteltestapp.domain.states

import com.example.hoteltestapp.domain.model.TourInfo

data class BookState(
    val tourInfo: TourInfo = TourInfo()
)