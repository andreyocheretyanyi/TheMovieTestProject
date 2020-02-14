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
        query: String,
        page: Int,
        includeAdult: Boolean,
        year: Int?
    ): Single<MovieDto> =
        movieService.searchMovie(BuildConfig.API_KEY, query, page, includeAdult, year)
            .subscribeOn(Schedulers.io())


    override fun discoverMovies(
        includeAdult: Boolean,
        page: Int,
        year: Int?,
        genres: String,
        people: String
    ): Single<MovieDto> =
        movieService.discoverMovie(BuildConfig.API_KEY, includeAdult, page, year, genres, people)
            .subscribeOn(Schedulers.io())


}