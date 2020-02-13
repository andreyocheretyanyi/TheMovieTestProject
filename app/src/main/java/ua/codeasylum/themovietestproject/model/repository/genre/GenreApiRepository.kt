package ua.codeasylum.themovietestproject.model.repository.genre

import ua.codeasylum.themovietestproject.base.service.GenreService
import ua.codeasylum.themovietestproject.di.scope.ApplicationScope
import javax.inject.Inject

@ApplicationScope
class GenreApiRepository @Inject constructor(private val genreService: GenreService) : GenreRepository  {
}