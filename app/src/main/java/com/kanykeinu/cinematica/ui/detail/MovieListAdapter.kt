package com.kanykeinu.cinematica.ui.detail

import android.util.Log
import com.kanykeinu.cinematica.R
import com.kanykeinu.cinematica.data.pojo.Movie
import com.kanykeinu.cinematica.data.remote.responses.MovieInfoResponse
import com.kanykeinu.cinematica.ui.base.BaseAdapter
import kotlinx.android.synthetic.main.movie_list_fragment.view.*
import kotlinx.android.synthetic.main.rv_movie_list_item.view.*

class MovieListAdapter : BaseAdapter<MovieInfoResponse>(R.layout.rv_movie_list_item) {

    override fun onBindViewHolder(holder: BaseHolder, position: Int, item: MovieInfoResponse) {
        val movie = list[position]
        holder.itemView.movie_cover.setImageURI(movie.getImageUrl())
        Log.d("Image url-->", movie.getImageUrl())
        holder.itemView.movie_title.text = movie.title
    }
}