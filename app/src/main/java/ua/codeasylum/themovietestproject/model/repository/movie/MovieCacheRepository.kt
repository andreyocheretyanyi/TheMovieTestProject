package ua.codeasylum.themovietestproject.model.repository.movie

import io.reactivex.Single
import ua.codeasylum.themovietestproject.di.scope.ApplicationScope
import ua.codeasylum.themovietestproject.model.networkDto.MovieDto
import ua.codeasylum.themovietestproject.model.repository.SaveToCacheWithKey
import javax.inject.Inject

@ApplicationScope
class MovieCacheRepository @Inject constructor() : MovieRepository,
    SaveToCacheWithKey<MovieArgs, MovieDto> {

    private val cachedMovies = HashMap<MovieArgs, MovieDto>()

    override fun searchMovies(
        movieArgs: MovieArgs
    ): Single<MovieDto> = Single.just(cachedMovies[movieArgs] ?: MovieDto())


    override fun discoverMovies(
        movieArgs: MovieArgs
    ): Single<MovieDto> = Single.just(cachedMovies[movieArgs] ?: MovieDto())


    override fun save(key: MovieArgs, value: MovieDto) {
        cachedMovies[key] = value
    }


}