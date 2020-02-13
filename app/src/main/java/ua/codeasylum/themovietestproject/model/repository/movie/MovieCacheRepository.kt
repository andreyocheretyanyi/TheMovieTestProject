package ua.codeasylum.themovietestproject.model.repository.movie

import ua.codeasylum.themovietestproject.di.scope.ApplicationScope
import javax.inject.Inject

@ApplicationScope
class MovieCacheRepository @Inject constructor() : MovieRepository {
}