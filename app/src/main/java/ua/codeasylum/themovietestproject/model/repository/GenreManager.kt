package ua.codeasylum.themovietestproject.model.repository

import ua.codeasylum.themovietestproject.model.repository.genre.GenreApiRepository
import ua.codeasylum.themovietestproject.model.repository.genre.GenreCacheRepository

class GenreManager constructor(private val genreApiRepository: GenreApiRepository, private val genreCacheRepository: GenreCacheRepository) : GenreManagerInterface {
}