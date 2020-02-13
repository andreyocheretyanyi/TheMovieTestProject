package ua.codeasylum.themovietestproject.di.module

import dagger.Module
import dagger.Provides
import ua.codeasylum.themovietestproject.di.scope.ActivityScope
import ua.codeasylum.themovietestproject.model.repository.*
import ua.codeasylum.themovietestproject.model.repository.genre.GenreApiRepository
import ua.codeasylum.themovietestproject.model.repository.genre.GenreCacheRepository
import ua.codeasylum.themovietestproject.model.repository.movie.MovieApiRepository
import ua.codeasylum.themovietestproject.model.repository.movie.MovieCacheRepository
import ua.codeasylum.themovietestproject.model.repository.people.PeopleApiRepository

@Module
class RepositoryModule {

    @ActivityScope
    @Provides
    fun provideMovieManager(
        movieCacheRepository: MovieCacheRepository,
        movieApiRepository: MovieApiRepository
    ): MovieManagerInterface = MovieManager(movieCacheRepository, movieApiRepository)

    @ActivityScope
    @Provides
    fun provideGenreManager(
        genreCacheRepository: GenreCacheRepository,
        genreApiRepository: GenreApiRepository
    ): GenreManagerInterface = GenreManager(genreApiRepository, genreCacheRepository)

    @ActivityScope
    @Provides
    fun providePeopleManager(
        peopleApiRepository: PeopleApiRepository
    ) : PeopleManagerInterface = PeopleManager(peopleApiRepository)
}