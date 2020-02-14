package ua.codeasylum.themovietestproject.model.repository.movie

import io.reactivex.Single
import ua.codeasylum.themovietestproject.model.networkDto.MovieDto

interface MovieRepository {


    fun searchMovies(query: String, page: Int, includeAdult: Boolean, year: Int?): Single<MovieDto>

    fun discoverMovies(
        includeAdult: Boolean,
        page: Int,
        year: Int?,
        genres: String,
        people: String
    ): Single<MovieDto>
}