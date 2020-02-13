package ua.codeasylum.themovietestproject.model.repository.genre

import ua.codeasylum.themovietestproject.di.scope.ApplicationScope
import javax.inject.Inject

@ApplicationScope
class GenreCacheRepository @Inject constructor() : GenreRepository {
}