package com.example.hoteltestapp.domain.repository

import com.example.hoteltestapp.RetrofitBuilder
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

open class BaseRepository: KoinComponent {
    protected val builder: RetrofitBuilder by inject()
}