package com.kanykeinu.cinematica.data.remote.responses

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import com.kanykeinu.cinematica.data.pojo.Genre
import kotlinx.android.parcel.Parcelize
import kotlinx.android.parcel.RawValue

const val POSTER_REQUEST_URL = "http://image.tmdb.org/t/p/w1280"
const val BACKDROP_REQUEST_URL = "http://image.tmdb.org/t/p/w1280"

@Parcelize
data class MovieInfoResponse(
    @SerializedName("backdrop_path")
    val backdropPath: String?,
    val budget: Int,
    val genres: List<Genre>,
    @SerializedName("original_language")
    val originalLanguage: String,
    @SerializedName("original_title")
    val originalTitle: String,
    @SerializedName("poster_path")
    val posterPath: String,
    val tagline: String,
    val title: String,
    @SerializedName("vote_average")
    val voteAverage: Double,
    @SerializedName("release_date")
    val releaseDate: String,
    val overview: String
) : Parcelable {

    fun getPosterImage(): String {
        return "$POSTER_REQUEST_URL${posterPath}"
    }

    fun getBackdropImage(): String {
        return if (backdropPath != null) {
            "$BACKDROP_REQUEST_URL${backdropPath}"
        } else {
            "$POSTER_REQUEST_URL${posterPath}"
        }
    }

    override fun toString(): String {
        return "$title - ${posterPath}\n"
    }
}