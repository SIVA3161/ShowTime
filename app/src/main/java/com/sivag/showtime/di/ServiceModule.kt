package com.sivag.showtime.di


import com.sivag.showtime.data.service.IMovieService
import com.sivag.showtime.data.service.impl.MovieServiceImpl
import io.ktor.client.HttpClient
import org.koin.dsl.module

val serviceModule = module {
    single<IMovieService> { provideMovieService(get()) }
}

fun provideMovieService(httpClient: HttpClient): MovieServiceImpl {
    return MovieServiceImpl(httpClient)
}