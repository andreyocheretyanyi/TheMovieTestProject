package ua.codeasylum.themovietestproject.model.repository.manager

import io.reactivex.Single
import ua.codeasylum.themovietestproject.di.scope.ActivityScope
import ua.codeasylum.themovietestproject.model.networkDto.MovieDto
import ua.codeasylum.themovietestproject.model.networkDto.MovieResult
import ua.codeasylum.themovietestproject.model.repository.movie.MovieApiRepository

@ActivityScope
class MovieManager constructor(
    private var movieApiRepository: MovieApiRepository
) : MovieManagerInterface {
    override fun fetchMoviesByAgruments(
        query: String,
        includeAdult: Boolean,
        page: Int,
        year: Int?,
        genres: String,
        people: String
    ): Single<MutableList<MovieResult>> =
        (if (genres.isEmpty() && people.isEmpty() && query.isNotEmpty())
            movieApiRepository.searchMovies(query, page, includeAdult, year)
        else movieApiRepository.discoverMovies(includeAdult, page, year, genres, people)
            .map { filterMoviesByName(query, it) }
                ).flatMap {
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