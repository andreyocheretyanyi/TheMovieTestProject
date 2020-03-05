package ua.codeasylum.themovietestproject.viewmodel

import androidx.navigation.NavController
import ua.codeasylum.themovietestproject.App
import ua.codeasylum.themovietestproject.model.dataSource.search.SearchMovieDataSourceFactory
import ua.codeasylum.themovietestproject.model.networkDto.MovieResult
import ua.codeasylum.themovietestproject.model.repository.manager.MovieManagerInterface
import ua.codeasylum.themovietestproject.view.SearchResultFragmentArgs
import ua.codeasylum.themovietestproject.view.SearchResultFragmentDirections
import javax.inject.Inject

class SearchResultViewModel @Inject constructor(
    app: App,
    movieManager: MovieManagerInterface,
    navigation: NavController
) : MovieListViewModel(app, navigation, movieManager) {

    lateinit var args: SearchResultFragmentArgs

    override fun onMovieClick(movie: MovieResult) {
        navigation.navigate(
            SearchResultFragmentDirections.actionSearchResultFragmentToMovieDetailFragment(
                movie
            )
        )
    }

    override fun initMovieListDataFactory() {
        movieDataSourceFactory =
            SearchMovieDataSourceFactory(
                movieManager,
                args.year,
                args.filmQuery,
                args.genresIds,
                args.personId,
                args.isAdult,
                error
            )
        configDataSource()
    }
}