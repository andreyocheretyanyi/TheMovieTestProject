package ua.codeasylum.themovietestproject.viewmodel

import ua.codeasylum.themovietestproject.App
import javax.inject.Inject

class TopMoviesViewModel @Inject constructor(app : App) : ListViewModel(app) {

    override fun fetchData() {

    }
}