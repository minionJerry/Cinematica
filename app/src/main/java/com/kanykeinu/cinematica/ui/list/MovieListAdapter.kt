package com.kanykeinu.cinematica.ui.list

import android.util.Log
import com.kanykeinu.cinematica.R
import com.kanykeinu.cinematica.data.remote.responses.MovieInfoResponse
import com.kanykeinu.cinematica.ui.base.BaseAdapter
import kotlinx.android.synthetic.main.rv_movie_list_item.view.*

class MovieListAdapter : BaseAdapter<MovieInfoResponse>(R.layout.rv_movie_list_item) {

    override fun onBindViewHolder(holder: BaseHolder, position: Int, item: MovieInfoResponse) {
        val movie = list[position]
        val imageInfo = movie.getPosterImage()
        holder.itemView.movie_cover.setImageURI(movie.getPosterImage())
        Log.d("Image url-->", movie.getPosterImage())
        holder.itemView.movie_title.text = movie.title
    }
}