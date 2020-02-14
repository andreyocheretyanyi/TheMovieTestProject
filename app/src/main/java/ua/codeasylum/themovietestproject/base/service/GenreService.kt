package ua.codeasylum.themovietestproject.base.service

import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query
import ua.codeasylum.themovietestproject.model.networkDto.GenreDto

interface GenreService {
    @GET("genre/movie/list")
    fun getGenres(@Query("api_key") apiKey: String) : Single<GenreDto>
}