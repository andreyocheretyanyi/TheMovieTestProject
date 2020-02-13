package ua.codeasylum.themovietestproject.base.service

import retrofit2.http.GET
import retrofit2.http.Query

interface MovieService {

    @GET("discover/movie")
    fun discoverMovie(
        @Query("api_key") apiKey: String, @Query("include_adult") includeAdult: Boolean,
        @Query("page") page: Int, @Query("year") year: Int, @Query("with_genres") genres: String,
        @Query("with_people") people: String
    )

    @GET("search/movie")
    fun searchMovie(
        @Query("api_key") apiKey: String, @Query(
            "query",
            encoded = true
        ) query: String, @Query("page") page: Int,
        @Query("include_adult") includeAdult: Boolean, @Query("year") year: Int
    )

}