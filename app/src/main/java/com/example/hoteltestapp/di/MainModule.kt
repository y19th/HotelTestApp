package com.example.hoteltestapp.di

import com.example.hoteltestapp.RetrofitBuilder
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val mainModule = module {

    single {
        RetrofitBuilder()
    }
}