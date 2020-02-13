package ua.codeasylum.themovietestproject.di.module

import dagger.Module
import dagger.Provides
import ua.codeasylum.themovietestproject.di.scope.ActivityScope
import ua.codeasylum.themovietestproject.model.repository.MovieManager
import ua.codeasylum.themovietestproject.model.repository.MovieManagerInterface
import ua.codeasylum.themovietestproject.model.repository.movie.MovieApiRepository
import ua.codeasylum.themovietestproject.model.repository.movie.MovieCacheRepository

@Module
class RepositoryModule {

    @ActivityScope
    @Provides
    fun provideMovieManager(movieCacheRepository: MovieCacheRepository, movieApiRepository: MovieApiRepository) : MovieManagerInterface
            = MovieManager(movieCacheRepository,movieApiRepository)
}