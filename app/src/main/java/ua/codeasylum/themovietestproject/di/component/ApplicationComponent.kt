package ua.codeasylum.themovietestproject.di.component

import dagger.BindsInstance
import dagger.Component
import ua.codeasylum.themovietestproject.App
import ua.codeasylum.themovietestproject.di.module.AppModule
import ua.codeasylum.themovietestproject.di.scope.ApplicationScope
import ua.codeasylum.themovietestproject.model.repository.genre.GenreApiRepository
import ua.codeasylum.themovietestproject.model.repository.genre.GenreCacheRepository
import ua.codeasylum.themovietestproject.model.repository.movie.MovieApiRepository
import ua.codeasylum.themovietestproject.model.repository.people.PeopleApiRepository


@ApplicationScope
@Component(
    modules = [
        AppModule::class]
)
interface ApplicationComponent {

    fun app() : App
    fun movieApiRepository() : MovieApiRepository
    fun genreApiReository() : GenreApiRepository
    fun genreCacheRepository() : GenreCacheRepository
    fun peopleApiRepository() : PeopleApiRepository

    @Component.Builder
    interface Builder{

        @BindsInstance
        fun withApp(app : App) : Builder

        fun build () : ApplicationComponent
    }

}