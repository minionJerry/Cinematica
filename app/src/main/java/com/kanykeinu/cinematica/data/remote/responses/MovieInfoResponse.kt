package com.kanykeinu.cinematica.data.remote.responses

import com.google.gson.annotations.SerializedName
import com.kanykeinu.cinematica.data.pojo.Genre

data class MovieInfoResponse(
    @SerializedName("backdrop_path")
    val backdropPath: String,
    val budget: Int,
    val genres : Array<Genre>,
    @SerializedName("original_language")
    val originalLanguage: String,
    @SerializedName("original_title")
    val originalTitle: String,
    @SerializedName("poster_path")
    val posterPath: String?,
    val tagline: String,
    val title: String,
    @SerializedName("vote_average")
    val voteAverage: Double
) {

    fun getImageUrl(): String {
        return "http://image.tmdb.org/t/p/w342$posterPath"
    }

    override fun toString(): String {
        return "$title - $posterPath\n"
    }
}