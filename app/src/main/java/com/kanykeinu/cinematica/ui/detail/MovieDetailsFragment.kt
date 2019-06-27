package com.kanykeinu.cinematica.ui.detail

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.navArgs

import com.kanykeinu.cinematica.R
import com.kanykeinu.cinematica.data.remote.responses.MovieInfoResponse
import com.kanykeinu.cinematica.ui.MainActivity


class MovieDetailsFragment : Fragment() {

    private lateinit var dataBinding: com.kanykeinu.cinematica.databinding.MovieDetailsFragmentBinding
    private lateinit var viewModel: MovieDetailsViewModel
    private val argument: MovieDetailsFragmentArgs by navArgs()
    private var movieInfo: MovieInfoResponse? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        movieInfo = argument.movieInfo
        (activity as MainActivity).supportActionBar?.title = movieInfo?.title
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        dataBinding = DataBindingUtil.inflate(inflater, R.layout.movie_details_fragment, container, false)
        return dataBinding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(MovieDetailsViewModel::class.java)
        bindData()
    }

    private fun bindData() {
        dataBinding.moviePoster.setImageURI(movieInfo?.getBackdropImage())
        dataBinding.tvMovieTitle.text = movieInfo?.originalTitle
        dataBinding.tvTagline.text = movieInfo?.tagline
        dataBinding.tvBudget.text = movieInfo?.budget.toString()
        dataBinding.tvReleaseDate.text = movieInfo?.releaseDate
        if (!movieInfo?.genres.isNullOrEmpty())
            dataBinding.tvGenre.text = movieInfo?.genres?.get(0)?.name
        dataBinding.tvOverview.text = movieInfo?.overview
        dataBinding.tvLanguage.text = movieInfo?.originalLanguage
        dataBinding.ratingBar.rating = movieInfo?.voteAverage!!.toFloat()/2
        dataBinding.tvRating.text = movieInfo?.voteAverage.toString()
    }

}
