package ua.codeasylum.themovietestproject.viewmodel.home

import androidx.navigation.NavController
import androidx.paging.DataSource
import ua.codeasylum.themovietestproject.App
import ua.codeasylum.themovietestproject.base.allMoviesDataSource
import ua.codeasylum.themovietestproject.model.dataSource.movies.MoviesDataSource
import ua.codeasylum.themovietestproject.model.dataSource.movies.MoviesDataSourceFactory
import ua.codeasylum.themovietestproject.model.networkDto.MovieResult
import ua.codeasylum.themovietestproject.model.repository.manager.MovieManagerInterface
import ua.codeasylum.themovietestproject.viewmodel.MovieListViewModel
import javax.inject.Inject
import javax.inject.Named

class AllMoviesViewModel @Inject constructor(
    app: App,
    movieManager: MovieManagerInterface,
    navigator: NavController,
    @Named(allMoviesDataSource)
    moviesDataSourceFactory: DataSource.Factory<Int, MovieResult>
) : MovieListViewModel(
    app,
    navigator,
    movieManager,
    moviesDataSourceFactory
)



