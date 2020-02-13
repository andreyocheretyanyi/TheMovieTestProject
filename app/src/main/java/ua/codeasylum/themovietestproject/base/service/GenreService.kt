package ua.codeasylum.themovietestproject.base.service

import retrofit2.http.GET
import retrofit2.http.Query

interface GenreService {
    @GET("genre/movie/list")
    fun getGenres(@Query("api_key") apiKey: String)
}