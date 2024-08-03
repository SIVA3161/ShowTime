package com.sivag.showtime.di

import com.sivag.network.di.networkModule
import org.koin.dsl.module

val koinModule = module {
    includes(appModule, viewModelModule, repositoryModule, serviceModule, networkModule)
}