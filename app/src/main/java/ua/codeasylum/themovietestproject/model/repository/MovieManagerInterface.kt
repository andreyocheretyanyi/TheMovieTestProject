package ua.codeasylum.themovietestproject.model.repository

import io.reactivex.Single
import ua.codeasylum.themovietestproject.model.networkDto.MovieDto

interface MovieManagerInterface {

    fun fetchMoviesByAgruments(
        query: String, includeAdult: Boolean,
        page: Int,
        year: Int?,
        genres: String,
        people: String
    ): Single<MovieDto>
}