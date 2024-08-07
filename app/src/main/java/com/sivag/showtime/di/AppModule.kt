package com.sivag.showtime.di

import android.content.Context
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val appModule = module {
    single { provideContext(androidContext()) }
}
fun provideContext(context: Context): Context {
    return context
}