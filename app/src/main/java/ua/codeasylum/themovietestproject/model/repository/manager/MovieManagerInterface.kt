package ua.codeasylum.themovietestproject.model.repository.manager

import io.reactivex.Single
import ua.codeasylum.themovietestproject.model.networkDto.MovieDto
import ua.codeasylum.themovietestproject.model.networkDto.MovieResult

interface MovieManagerInterface {

    fun fetchMoviesByAgruments(
        query: String, includeAdult: Boolean,
        page: Int,
        year: Int?,
        genres: String,
        people: String
    ): Single<MutableList<MovieResult>>
}