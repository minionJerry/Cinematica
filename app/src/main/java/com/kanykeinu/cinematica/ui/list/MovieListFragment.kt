package com.kanykeinu.cinematica.ui.list

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil

import com.kanykeinu.cinematica.R
import com.kanykeinu.cinematica.databinding.MovieListFragmentBinding
import com.kanykeinu.cinematica.injection.Injector
import com.kanykeinu.cinematica.ui.detail.MovieListAdapter
import com.kanykeinu.cinematica.util.observe
import com.kanykeinu.cinematica.util.showSnackBar

class MovieListFragment : Fragment() {

    private lateinit var movieAdapter: MovieListAdapter
    private lateinit var dataBinding: MovieListFragmentBinding
    private lateinit var viewModel: MovieListViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val viewModelFactory = Injector.provideMovieListViewModelFactory()
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(MovieListViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        dataBinding = DataBindingUtil.inflate(inflater, R.layout.movie_list_fragment, container, false)
        return dataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        dataBinding.viewModel = viewModel
        initViews()
        observeData()
        getMovies()
    }

    private fun initViews(){
        movieAdapter = MovieListAdapter()
        dataBinding.rvMovieList.setHasFixedSize(true)
        dataBinding.rvMovieList.adapter = movieAdapter
    }

    private fun observeData(){
        viewModel.movies.observe(this){
            Log.d("movies ->", it.toString())
            movieAdapter.list = it
        }

        viewModel.error.observe(this){
            dataBinding.root.showSnackBar(it)
            Log.d("Error->", it)
        }
    }

    private fun getMovies(){
        viewModel.loadMovieChanges(null,null,1)
    }

}
