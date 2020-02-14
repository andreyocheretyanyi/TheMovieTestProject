package ua.codeasylum.themovietestproject.model.repository.genre

import io.reactivex.Single
import ua.codeasylum.themovietestproject.di.scope.ApplicationScope
import ua.codeasylum.themovietestproject.model.networkDto.GenreDto
import ua.codeasylum.themovietestproject.model.repository.CanSaveToCache
import javax.inject.Inject

@ApplicationScope
class GenreCacheRepository @Inject constructor() : GenreRepository, CanSaveToCache<GenreDto> {

    private var genreDto = GenreDto()

    override fun getGenres(): Single<GenreDto> = Single.just(genreDto)


    override fun save(data: GenreDto) {
        genreDto = data
    }
}