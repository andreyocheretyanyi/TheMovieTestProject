package ua.codeasylum.themovietestproject.model.repository


import io.reactivex.Single

import ua.codeasylum.themovietestproject.model.networkDto.GenreDto
import ua.codeasylum.themovietestproject.model.repository.genre.GenreApiRepository
import ua.codeasylum.themovietestproject.model.repository.genre.GenreCacheRepository

class GenreManager constructor(
    private val genreApiRepository: GenreApiRepository,
    private val genreCacheRepository: GenreCacheRepository
) : GenreManagerInterface {

    override fun fetchGenres(): Single<GenreDto> =
        genreCacheRepository.getGenres()
            .flatMap {
                if (it.genres.isEmpty())
                    genreApiRepository.getGenres()
                        .doOnDispose {
                            genreCacheRepository.save(it)
                        }
                else Single.just(it)
            }

}