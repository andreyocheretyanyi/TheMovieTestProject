package ua.codeasylum.themovietestproject.di.module

import dagger.Module
import dagger.Provides
import ua.codeasylum.themovietestproject.di.scope.ActivityScope
import ua.codeasylum.themovietestproject.model.repository.*
import ua.codeasylum.themovietestproject.model.repository.genre.GenreApiRepository
import ua.codeasylum.themovietestproject.model.repository.genre.GenreCacheRepository
import ua.codeasylum.themovietestproject.model.repository.movie.MovieApiRepository
import ua.codeasylum.themovietestproject.model.repository.people.PeopleApiRepository
import ua.codeasylum.themovietestproject.model.repository.people.PeopleCacheRepository

@Module
class RepositoryModule {

    @ActivityScope
    @Provides
    fun provideMovieManager(
        movieApiRepository: MovieApiRepository
    ): MovieManagerInterface = MovieManager(movieApiRepository)

    @ActivityScope
    @Provides
    fun provideGenreManager(
        genreCacheRepository: GenreCacheRepository,
        genreApiRepository: GenreApiRepository
    ): GenreManagerInterface = GenreManager(genreApiRepository, genreCacheRepository)

    @ActivityScope
    @Provides
    fun providePeopleManager(
        peopleApiRepository: PeopleApiRepository,
        peopleCacheRepository: PeopleCacheRepository
    ): PeopleManagerInterface = PeopleManager(peopleApiRepository, peopleCacheRepository)
}