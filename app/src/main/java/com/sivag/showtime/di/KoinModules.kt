package com.sivag.showtime.di

import com.sivag.network.sample.di.networkModule
import org.koin.dsl.module

val koinModule = module {
    includes(viewModelModule, networkModule)
}