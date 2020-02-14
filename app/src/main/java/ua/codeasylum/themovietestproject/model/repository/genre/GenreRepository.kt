package ua.codeasylum.themovietestproject.model.repository.genre

import io.reactivex.Single
import ua.codeasylum.themovietestproject.model.networkDto.GenreDto

interface GenreRepository {

    fun getGenres() : Single<GenreDto>
}