package ua.codeasylum.themovietestproject.model.repository.movie

import ua.codeasylum.themovietestproject.base.service.MovieService
import ua.codeasylum.themovietestproject.di.scope.ApplicationScope
import javax.inject.Inject

@ApplicationScope
class MovieApiRepository @Inject constructor(private val movieService : MovieService) : MovieRepository {
}