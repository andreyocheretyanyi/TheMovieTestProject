package ua.codeasylum.themovietestproject.base.service

import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query
import ua.codeasylum.themovietestproject.model.networkDto.MovieDto

interface MovieService {

    @GET("discover/movie")
    fun discoverMovie(
        @Query("api_key") apiKey: String, @Query("include_adult") includeAdult: Boolean,
        @Query("page") page: Int, @Query("primary_release_year") year: Int?, @Query(
            "with_genres",
            encoded = true
        ) genres: String,
        @Query("with_people") people: String
    ): Single<MovieDto>

    @GET("search/movie")
    fun searchMovie(
        @Query("api_key") apiKey: String, @Query(
            "query",
            encoded = true
        ) query: String, @Query("page") page: Int,
        @Query("include_adult") includeAdult: Boolean, @Query("year") year: Int?
    ): Single<MovieDto>

}