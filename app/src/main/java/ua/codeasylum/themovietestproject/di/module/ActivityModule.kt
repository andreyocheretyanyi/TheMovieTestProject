package ua.codeasylum.themovietestproject.di.module

import android.app.Activity
import androidx.navigation.NavController
import androidx.navigation.findNavController
import dagger.Module
import dagger.Provides
import ua.codeasylum.themovietestproject.R
import ua.codeasylum.themovietestproject.base.allMoviesDataSource
import ua.codeasylum.themovietestproject.base.searchDataSource
import ua.codeasylum.themovietestproject.base.topRatedMoviesDataSource
import ua.codeasylum.themovietestproject.base.upcomingMoviesDataSource
import ua.codeasylum.themovietestproject.di.scope.ActivityScope
import ua.codeasylum.themovietestproject.model.dataSource.MovieDataSourceFactoryBase
import ua.codeasylum.themovietestproject.model.dataSource.movies.MoviesDataSource
import ua.codeasylum.themovietestproject.model.dataSource.movies.MoviesDataSourceFactory
import ua.codeasylum.themovietestproject.model.dataSource.search.SearchMovieDataSource
import ua.codeasylum.themovietestproject.model.dataSource.search.SearchMovieDataSourceFactory
import ua.codeasylum.themovietestproject.model.repository.manager.MovieManagerInterface
import javax.inject.Named

@Module
class ActivityModule {


    @Provides
    @ActivityScope
    fun provideNavigation(activity: Activity): NavController =
        activity.findNavController(R.id.nav_host_fragment)

    @Provides
    @Named(searchDataSource)
    @ActivityScope

    fun provideSearchDataSourceFactory(movieManger: MovieManagerInterface): MovieDataSourceFactoryBase =
        SearchMovieDataSourceFactory(SearchMovieDataSource(movieManger))

    @Provides
    @Named(allMoviesDataSource)
    @ActivityScope

    fun provideAllMoviesDataSource(movieManger: MovieManagerInterface): MovieDataSourceFactoryBase =
        MoviesDataSourceFactory(
            MoviesDataSource.RequestType.All,
            MoviesDataSource(MoviesDataSource.RequestType.All, movieManger)
        )

    @Provides
    @Named(topRatedMoviesDataSource)
    @ActivityScope

    fun provideTopMoviesDataSource(movieManger: MovieManagerInterface): MovieDataSourceFactoryBase =
        MoviesDataSourceFactory(
            MoviesDataSource.RequestType.TopRated,
            MoviesDataSource(MoviesDataSource.RequestType.TopRated, movieManger)
        )

    @Provides
    @Named(upcomingMoviesDataSource)
    @ActivityScope
    fun provideUpcomingMoviesDataSource(movieManger: MovieManagerInterface): MovieDataSourceFactoryBase =
        MoviesDataSourceFactory(
            MoviesDataSource.RequestType.Upcoming,
            MoviesDataSource(MoviesDataSource.RequestType.Upcoming, movieManger)
        )

}