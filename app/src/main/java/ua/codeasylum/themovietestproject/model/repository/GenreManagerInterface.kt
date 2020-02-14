package ua.codeasylum.themovietestproject.model.repository

import io.reactivex.Single
import ua.codeasylum.themovietestproject.model.networkDto.GenreDto

interface GenreManagerInterface {

    fun fetchGenres(): Single<GenreDto>

}