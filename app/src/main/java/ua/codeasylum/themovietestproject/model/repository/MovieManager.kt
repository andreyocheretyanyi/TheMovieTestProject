package ua.codeasylum.themovietestproject.model.repository

import ua.codeasylum.themovietestproject.di.scope.ActivityScope
import ua.codeasylum.themovietestproject.model.repository.movie.MovieApiRepository
import ua.codeasylum.themovietestproject.model.repository.movie.MovieCacheRepository
import javax.inject.Inject

@ActivityScope
class MovieManager constructor(private var movieCacheRepository: MovieCacheRepository,
                                       private var movieApiRepository: MovieApiRepository) : MovieManagerInterface {
}