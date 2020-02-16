package ua.codeasylum.themovietestproject.model.repository.manager

import io.reactivex.Single
import ua.codeasylum.themovietestproject.model.networkDto.GenreDto

interface GenreManagerInterface {

    fun fetchGenres(): Single<GenreDto>

    fun getGenresStringById(list: MutableList<Int>): Single<String>



}