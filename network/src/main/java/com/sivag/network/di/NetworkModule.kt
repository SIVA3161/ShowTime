package com.sivag.network.di

import com.sivag.network.client.ApiService
import com.sivag.network.client.httpClientAndroid
import io.ktor.client.HttpClient
import org.koin.dsl.module

val networkModule = module {
    single { provideApiService(get()) }
    single { provideHttpClient() }
}

fun provideHttpClient(): HttpClient {
    return httpClientAndroid
}

fun provideApiService(httpClient: HttpClient): ApiService {
    return ApiService(httpClient)
}
