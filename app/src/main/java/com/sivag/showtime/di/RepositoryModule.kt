package com.sivag.showtime.di


import com.sivag.showtime.data.repository.IMovieRepository
import com.sivag.showtime.data.repository.impl.MovieRepositoryImpl
import com.sivag.showtime.data.service.IMovieService
import org.koin.dsl.module

val repositoryModule = module {
    single<IMovieRepository> { provideMovieRepository(get()) }
}

fun provideMovieRepository(apiService: IMovieService): MovieRepositoryImpl {
    return MovieRepositoryImpl(apiService)
}
