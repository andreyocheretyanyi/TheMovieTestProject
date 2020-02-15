package ua.codeasylum.themovietestproject.model.repository.manager

import io.reactivex.Single
import ua.codeasylum.themovietestproject.di.scope.ActivityScope
import ua.codeasylum.themovietestproject.model.networkDto.MovieDto
import ua.codeasylum.themovietestproject.model.networkDto.MoviesResult
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
    ): Single<MovieDto> =
        if (genres.isEmpty() && people.isEmpty() && query.isNotEmpty())
            movieApiRepository.searchMovies(query, page, includeAdult, year)
        else movieApiRepository.discoverMovies(includeAdult, page, year, genres, people)
            .map { filterMoviesByName(query, it) }

    private fun filterMoviesByName(name: String, dto: MovieDto): MovieDto = dto.apply {
        val resultList = ArrayList<MoviesResult>()
        for (movieResult: MoviesResult in dto.results) {
            if (movieResult.title.contains(name, false) ||
                movieResult.originalTitle.contains(name, false)
            )
                resultList.add(movieResult)
        }
        if (resultList.isNotEmpty())
            results = resultList
    }


}