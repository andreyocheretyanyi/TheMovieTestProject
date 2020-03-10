package ua.codeasylum.themovietestproject.viewmodel.home

import androidx.navigation.NavController
import ua.codeasylum.themovietestproject.App
import ua.codeasylum.themovietestproject.base.topRatedMoviesDataSource
import ua.codeasylum.themovietestproject.model.dataSource.MovieDataSourceFactoryBase
import ua.codeasylum.themovietestproject.model.repository.manager.MovieManagerInterface
import ua.codeasylum.themovietestproject.viewmodel.MovieListViewModel
import javax.inject.Inject
import javax.inject.Named

class TopMoviesViewModel @Inject constructor(
    app: App,
    movieManager: MovieManagerInterface,
    navigator: NavController,
    @Named(topRatedMoviesDataSource)
    moviesDataSourceFactory: MovieDataSourceFactoryBase
) : MovieListViewModel(
    app,
    navigator,
    movieManager,
    moviesDataSourceFactory
)

