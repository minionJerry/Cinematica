package com.kanykeinu.cinematica.injection

import com.kanykeinu.cinematica.data.remote.MovieDbApi
import com.kanykeinu.cinematica.data.remote.Retrofit
import com.kanykeinu.cinematica.ui.list.MovieListViewModelFactory

object Injector {

    fun provideApiService() : MovieDbApi{
        return Retrofit.getApi()
    }

    fun provideMovieListViewModelFactory() : MovieListViewModelFactory{
        return MovieListViewModelFactory.getInstance(provideApiService())
    }
}