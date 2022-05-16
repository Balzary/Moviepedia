package com.example.moviepedia.util

import com.example.moviepedia.model.MovieResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiInterface {

    @GET("3/movie/now_playing?api_key=f321a808e68611f41312aa8408531476&language=en-US")
    fun getMovieList(@Query("page") page : Int) : Call<MovieResponse>

}