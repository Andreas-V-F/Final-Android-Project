package com.example.androidfinalproject.api

import com.example.androidfinalproject.models.PopularMovies
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface TmdbEndpoints {
    @GET("/3/movie/popular")
    fun getPopMovies(@Query("api_key") key: String): Call<PopularMovies>

    @GET("/3/movie/top_rated")
    fun getTopMovies(@Query("api_key") key: String): Call<PopularMovies>

    @GET("/3/search/movie")
    fun searchMovies(@Query("api_key") key: String, @Query("query") query : String): Call<PopularMovies>
}