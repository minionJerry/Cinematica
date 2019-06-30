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

const val MOVIE_KEY = "MOVIE"

class MovieDetailsFragment : Fragment() {

    private lateinit var dataBinding: com.kanykeinu.cinematica.databinding.MovieDetailsFragmentBinding
    private val argument: MovieDetailsFragmentArgs by navArgs()
    private var movieInfo: MovieInfoResponse? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        movieInfo = if (savedInstanceState != null) savedInstanceState.getParcelable(MOVIE_KEY)
        else
            argument.movieInfo
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
        (activity as MainActivity).supportActionBar?.title = movieInfo?.title
        bindData()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putParcelable(
            MOVIE_KEY,
            movieInfo
        )
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
        dataBinding.ratingBar.rating = movieInfo?.voteAverage!!.toFloat() / 2
        dataBinding.tvRating.text = movieInfo?.voteAverage.toString()
    }

}
