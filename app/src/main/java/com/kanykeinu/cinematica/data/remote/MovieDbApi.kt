package com.kanykeinu.cinematica.data.remote

import com.kanykeinu.cinematica.data.remote.responses.MovieChangesResponse
import com.kanykeinu.cinematica.data.remote.responses.MovieInfoResponse
import com.kanykeinu.cinematica.data.remote.responses.Results
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieDbApi {

    @GET("/3/movie/changes")
    fun getMoviesChanges(@Query("start_date") startDate : String?, @Query("end_date") endDate : String?, @Query("page") page : Int?) : Observable<Results>

    @GET("3/movie/{movie_id}")
    fun getMovieInfo(@Path("movie_id") movieId : Int, @Query("append_to_response") appendToResponse : String = "images") : Observable<MovieInfoResponse>


}