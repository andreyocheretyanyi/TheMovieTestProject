package ua.codeasylum.themovietestproject.model.repository.manager

import io.reactivex.Single
import ua.codeasylum.themovietestproject.di.scope.ActivityScope
import ua.codeasylum.themovietestproject.model.networkDto.MovieDto
import ua.codeasylum.themovietestproject.model.networkDto.MovieResult
import ua.codeasylum.themovietestproject.model.repository.movie.MovieApiRepository
import ua.codeasylum.themovietestproject.model.repository.movie.MovieArgs
import ua.codeasylum.themovietestproject.model.repository.movie.MovieCacheRepository

@ActivityScope
class MovieManager constructor(
    private val movieApiRepository: MovieApiRepository,
    private val movieCacheRepository: MovieCacheRepository
) : MovieManagerInterface {
    override fun fetchMoviesByAgruments(
        movieArgs: MovieArgs
    ): Single<MutableList<MovieResult>> =
        movieCacheRepository.searchMovies(movieArgs)
            .flatMap { cachedDto ->
                if (cachedDto.results.isNotEmpty())
                    Single.just(cachedDto)
                else
                    (if (movieArgs.genres.isEmpty() && movieArgs.people.isEmpty() && movieArgs.query.isNotEmpty())
                        movieApiRepository.searchMovies(movieArgs)
                    else
                        movieApiRepository.discoverMovies(movieArgs)
                            .map { filterMoviesByName(movieArgs.query, it) })
                        .map {
                            movieCacheRepository.save(movieArgs, it)
                            return@map it
                        }
            }
            .flatMap {
                fillMovies(it)
            }

    private fun fillMovies(movieDto: MovieDto): Single<MutableList<MovieResult>> {
        val result = mutableListOf<MovieResult>()
        for (movie in movieDto.results) {
            movie.page = movieDto.page
            movie.totalPages = movieDto.totalPages
            result.add(movie)
        }
        return Single.just(result)
    }

    private fun filterMoviesByName(name: String, dto: MovieDto): MovieDto = dto.apply {
        val resultList = ArrayList<MovieResult>()
        for (movieResult: MovieResult in dto.results) {
            if (movieResult.title.contains(name, false) ||
                movieResult.originalTitle.contains(name, false)
            )
                resultList.add(movieResult)
        }
        if (resultList.isNotEmpty())
            results = resultList
    }


}