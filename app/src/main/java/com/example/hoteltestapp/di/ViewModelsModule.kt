package com.example.hoteltestapp.di

import com.example.hoteltestapp.domain.viewmodels.HotelViewModel
import com.example.hoteltestapp.domain.viewmodels.RoomViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelsModule = module {

    viewModel {
        HotelViewModel()
    }

    viewModel {
        RoomViewModel()
    }
}