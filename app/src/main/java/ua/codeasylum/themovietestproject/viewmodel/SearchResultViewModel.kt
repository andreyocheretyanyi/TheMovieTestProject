package ua.codeasylum.themovietestproject.viewmodel

import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.navigation.NavController
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import ua.codeasylum.themovietestproject.App
import ua.codeasylum.themovietestproject.base.notifyObserver
import ua.codeasylum.themovietestproject.model.dataSource.MovieDataSourceFactory
import ua.codeasylum.themovietestproject.model.networkDto.MovieResult
import ua.codeasylum.themovietestproject.model.networkDto.Person
import ua.codeasylum.themovietestproject.model.repository.manager.MovieManagerInterface
import ua.codeasylum.themovietestproject.view.SearchResultFragmentDirections
import javax.inject.Inject

class SearchResultViewModel @Inject constructor(
    app: App,
    private val movieManager: MovieManagerInterface,
    private val navigation: NavController
) : AndroidViewModel(app) {
    private lateinit var movieDataFactory: MovieDataSourceFactory

    var movies: LiveData<PagedList<MovieResult>> = MutableLiveData()
    val haveToNotifyBindingAdapter = MutableLiveData(1)

    fun initMovieDataFactory(
        year: String,
        movieQuery: String,
        genresIds: String,
        personId: String,
        isAdult: Boolean
    ) {
        movieDataFactory =
            MovieDataSourceFactory(movieManager, year, movieQuery, genresIds, personId, isAdult)

        val config = PagedList.Config.Builder()
            .setPageSize(20)
            .setEnablePlaceholders(false)
            .build()
        movies =
            LivePagedListBuilder<Int, MovieResult>(movieDataFactory, config).build()
        haveToNotifyBindingAdapter.notifyObserver()
    }


    fun onMovieClick(movie: MovieResult) {
        navigation.navigate(
            SearchResultFragmentDirections.actionSearchResultFragmentToMovieDetailFragment(
                movie
            )
        )
    }
}