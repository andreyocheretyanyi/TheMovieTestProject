package ua.codeasylum.themovietestproject.viewmodel.home

import androidx.navigation.NavController
import ua.codeasylum.themovietestproject.App
import ua.codeasylum.themovietestproject.model.dataSource.movies.MoviesDataSource
import ua.codeasylum.themovietestproject.model.dataSource.movies.MoviesDataSourceFactory
import ua.codeasylum.themovietestproject.model.repository.manager.MovieManagerInterface
import ua.codeasylum.themovietestproject.viewmodel.MovieListViewModel
import javax.inject.Inject

class TopMoviesViewModel @Inject constructor(
    app: App,
    movieManager: MovieManagerInterface,
    navigator: NavController
) : MovieListViewModel(
    app,
    navigator,
    movieManager
) {

    override fun initMovieListDataFactory() {
        movieDataSourceFactory =
            MoviesDataSourceFactory(MoviesDataSource.RequestType.TopRated, movieManager, error)
        configDataSource()
    }
}