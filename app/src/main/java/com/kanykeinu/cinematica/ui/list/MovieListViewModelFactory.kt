package com.kanykeinu.cinematica.ui.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.kanykeinu.cinematica.data.remote.MovieDbApi
import com.kanykeinu.cinematica.util.SingletonHolder

class MovieListViewModelFactory private constructor(private val api: MovieDbApi) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MovieListViewModel::class.java)) {
            return MovieListViewModel(api) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }

    companion object : SingletonHolder<MovieListViewModelFactory, MovieDbApi>(::MovieListViewModelFactory)
}