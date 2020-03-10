package ua.codeasylum.themovietestproject.viewmodel

import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.navigation.NavController
import androidx.paging.DataSource
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import ua.codeasylum.themovietestproject.App
import ua.codeasylum.themovietestproject.base.notifyObserver
import ua.codeasylum.themovietestproject.model.networkDto.MovieResult
import ua.codeasylum.themovietestproject.model.repository.manager.MovieManagerInterface
import ua.codeasylum.themovietestproject.view.home.HomeFragmentDirections

abstract class MovieListViewModel(
    app: App,
    protected val navigation: NavController,
    val movieManager: MovieManagerInterface,
    dataSourceFactory: DataSource.Factory<Int, MovieResult>
) : AndroidViewModel(app) {

    protected var dataSourceFactory: DataSource.Factory<Int, MovieResult>? = null
        set(value) {
            if (value != null) {
                field = value
                configDataSource(value)
            }
        }

    lateinit var movies: LiveData<PagedList<MovieResult>>
    val haveToNotifyBindingAdapter = MutableLiveData(1)
    val error = MutableLiveData("")

    init {
        this.dataSourceFactory = dataSourceFactory
    }


   private fun configDataSource(dataSourceFactory: DataSource.Factory<Int, MovieResult>) {
        val config = PagedList.Config.Builder()
            .setPageSize(20)
            .setEnablePlaceholders(false)
            .build()
        movies =
            LivePagedListBuilder<Int, MovieResult>(dataSourceFactory, config).build()
        haveToNotifyBindingAdapter.notifyObserver()
    }

    open fun onMovieClick(movie: MovieResult) {
        navigation.navigate(
            HomeFragmentDirections.actionHomeFragmentToMovieDetailFragment(movie)
        )
    }

}