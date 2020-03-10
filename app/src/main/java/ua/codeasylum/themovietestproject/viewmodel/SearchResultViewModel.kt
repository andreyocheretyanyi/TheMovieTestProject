package ua.codeasylum.themovietestproject.viewmodel

import androidx.navigation.NavController
import androidx.paging.DataSource
import ua.codeasylum.themovietestproject.App
import ua.codeasylum.themovietestproject.base.searchDataSource
import ua.codeasylum.themovietestproject.model.dataSource.movies.MoviesDataSourceFactory
import ua.codeasylum.themovietestproject.model.dataSource.search.SearchMovieDataSourceFactory
import ua.codeasylum.themovietestproject.model.networkDto.MovieResult
import ua.codeasylum.themovietestproject.model.repository.manager.MovieManagerInterface
import ua.codeasylum.themovietestproject.view.search.SearchResultFragmentArgs
import ua.codeasylum.themovietestproject.view.search.SearchResultFragmentDirections
import javax.inject.Inject
import javax.inject.Named

class SearchResultViewModel @Inject constructor(
    app: App,
    movieManager: MovieManagerInterface,
    navigation: NavController,
    @Named(searchDataSource)
    moviesDataSourceFactory: DataSource.Factory<Int, MovieResult>
) : MovieListViewModel(app, navigation, movieManager, moviesDataSourceFactory) {

    var args: SearchResultFragmentArgs? = null
        set(value) {
            value?.apply {
                (dataSourceFactory as SearchMovieDataSourceFactory).updateArgs(
                    year,
                    filmQuery,
                    genresIds,
                    personId,
                    isAdult
                )
                field = value
            }
        }

    override fun onMovieClick(movie: MovieResult) {
        navigation.navigate(
            SearchResultFragmentDirections.actionSearchResultFragmentToMovieDetailFragment(
                movie
            )
        )
    }

}