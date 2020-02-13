package ua.codeasylum.themovietestproject.viewmodel

import androidx.lifecycle.AndroidViewModel
import ua.codeasylum.themovietestproject.App
import ua.codeasylum.themovietestproject.model.repository.MovieManager
import ua.codeasylum.themovietestproject.model.repository.MovieManagerInterface
import javax.inject.Inject

class SearchViewModel @Inject constructor(app: App, private val movieManager: MovieManagerInterface) : AndroidViewModel(app) {
}