package ua.codeasylum.themovietestproject.viewmodel

import androidx.lifecycle.AndroidViewModel
import ua.codeasylum.themovietestproject.App
import ua.codeasylum.themovietestproject.model.repository.manager.MovieManagerInterface
import javax.inject.Inject

class SearchResultViewModel @Inject constructor(app : App, private val movieManager: MovieManagerInterface) : AndroidViewModel(app) {
}