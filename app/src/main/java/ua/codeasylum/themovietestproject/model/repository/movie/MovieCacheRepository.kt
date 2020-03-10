package ua.codeasylum.themovietestproject.model.repository.movie

import android.util.SparseArray
import io.reactivex.Single
import ua.codeasylum.themovietestproject.di.scope.ApplicationScope
import ua.codeasylum.themovietestproject.model.networkDto.MovieDto
import ua.codeasylum.themovietestproject.model.repository.SaveToCacheWithKey
import javax.inject.Inject

@ApplicationScope
class MovieCacheRepository @Inject constructor() : MovieRepository,
    SaveToCacheWithKey<MovieArgs, MovieDto>, SaveMoviesToCache {

    private val cachedSearchMovies = HashMap<MovieArgs, MovieDto>()
    private val cachedTopRated = SparseArray<MovieDto>()
    private val cachedUpcoming = SparseArray<MovieDto>()
    private val cachedAll = SparseArray<MovieDto>()

    override fun searchMovies(
        movieArgs: MovieArgs
    ): Single<MovieDto> = Single.just(cachedSearchMovies[movieArgs] ?: MovieDto())


    override fun discoverMovies(
        movieArgs: MovieArgs
    ): Single<MovieDto> = Single.just(cachedSearchMovies[movieArgs] ?: MovieDto())

    override fun getUpcoming(page: Int): Single<MovieDto> = Single.just(
        cachedUpcoming[page] ?: MovieDto()
    )


    override fun getTopRated(page: Int): Single<MovieDto> = Single.just(
        cachedTopRated[page] ?: MovieDto()
    )

    override fun getAll(page: Int): Single<MovieDto> = Single.just(
        cachedAll[page] ?: MovieDto()
    )

    override fun save(key: MovieArgs, value: MovieDto) {
        cachedSearchMovies[key] = value
    }

    override fun saveUpcoming(movieDto: MovieDto) {
        cachedUpcoming.put(movieDto.page, movieDto)
    }

    override fun saveTopRated(movieDto: MovieDto) {
        cachedTopRated.put(movieDto.page, movieDto)
    }

    override fun saveAll(movieDto: MovieDto) {
        cachedAll.put(movieDto.page, movieDto)
    }


}
