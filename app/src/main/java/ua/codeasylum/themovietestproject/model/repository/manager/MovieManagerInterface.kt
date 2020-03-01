package ua.codeasylum.themovietestproject.model.repository.manager

import io.reactivex.Single
import ua.codeasylum.themovietestproject.model.networkDto.MovieDto
import ua.codeasylum.themovietestproject.model.networkDto.MovieResult
import ua.codeasylum.themovietestproject.model.repository.movie.MovieArgs

interface MovieManagerInterface {

    fun fetchMoviesByAgruments(
        movieArgs: MovieArgs
    ): Single<MutableList<MovieResult>>

    fun fetchTopRated(page : Int) : Single<MutableList<MovieResult>>

    fun fetchUpcoming(page : Int) : Single<MutableList<MovieResult>>
}