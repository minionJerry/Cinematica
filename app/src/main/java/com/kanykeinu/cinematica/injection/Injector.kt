package com.kanykeinu.cinematica.injection

import android.content.Context
import com.kanykeinu.cinematica.data.remote.MovieDbApi
import com.kanykeinu.cinematica.data.remote.Retrofit
import com.kanykeinu.cinematica.ui.list.MovieListViewModelFactory

object Injector {

    private fun provideApiService(context: Context) : MovieDbApi{
        return Retrofit.getApi(context)
    }

    fun provideMovieListViewModelFactory(context: Context) : MovieListViewModelFactory{
        return MovieListViewModelFactory.getInstance(provideApiService(context))
    }
}