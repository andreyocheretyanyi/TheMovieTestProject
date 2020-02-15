package ua.codeasylum.themovietestproject.viewmodel

import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import ua.codeasylum.themovietestproject.App
import ua.codeasylum.themovietestproject.model.networkDto.MovieResult
import ua.codeasylum.themovietestproject.model.repository.manager.MovieManagerInterface
import ua.codeasylum.themovietestproject.view.MovieDetailFragmentArgs
import javax.inject.Inject

class MovieDetailViewModel @Inject constructor(
    app: App,
    private val movieManager: MovieManagerInterface
) : AndroidViewModel(app) {
    val movie = MutableLiveData<String>()

    fun performArgs(movieDetailFragmentArgs: MovieDetailFragmentArgs) {
        movie.value = movieDetailFragmentArgs.movie.toString()

    }
}