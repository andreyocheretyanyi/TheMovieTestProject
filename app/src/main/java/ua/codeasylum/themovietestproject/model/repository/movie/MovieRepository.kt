package ua.codeasylum.themovietestproject.model.repository.movie

import io.reactivex.Single
import ua.codeasylum.themovietestproject.model.networkDto.MovieDto

interface MovieRepository {


    fun searchMovies(movieArgs: MovieArgs): Single<MovieDto>

    fun discoverMovies(movieArgs: MovieArgs
    ): Single<MovieDto>

    fun getUpcoming(page : Int) : Single<MovieDto>

    fun getTopRated(page : Int) : Single<MovieDto>

    fun getAll(page : Int) : Single<MovieDto>
}


data class MovieArgs(
    val query: String,
    val includeAdult: Boolean,
    val page: Int,
    val year: Int?,
    val genres: String,
    val people: String
)