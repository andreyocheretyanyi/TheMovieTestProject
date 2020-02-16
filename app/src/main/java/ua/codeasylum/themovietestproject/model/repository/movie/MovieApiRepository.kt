package ua.codeasylum.themovietestproject.model.repository.movie

import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import ua.codeasylum.themovietestproject.BuildConfig
import ua.codeasylum.themovietestproject.base.service.MovieService
import ua.codeasylum.themovietestproject.di.scope.ApplicationScope
import ua.codeasylum.themovietestproject.model.networkDto.MovieDto
import javax.inject.Inject

@ApplicationScope
class MovieApiRepository @Inject constructor(private val movieService: MovieService) :
    MovieRepository {

    override fun searchMovies(
        movieArgs: MovieArgs
    ): Single<MovieDto> =
        movieService.searchMovie(
            BuildConfig.API_KEY,
            movieArgs.query,
            movieArgs.page,
            movieArgs.includeAdult,
            movieArgs.year
        )
            .subscribeOn(Schedulers.io())


    override fun discoverMovies(
        movieArgs: MovieArgs
    ): Single<MovieDto> =
        movieService.discoverMovie(BuildConfig.API_KEY, movieArgs.includeAdult, movieArgs.page, movieArgs.year, movieArgs.genres, movieArgs.people)
            .subscribeOn(Schedulers.io())


}