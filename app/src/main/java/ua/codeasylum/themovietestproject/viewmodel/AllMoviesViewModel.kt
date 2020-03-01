package ua.codeasylum.themovietestproject.viewmodel

import ua.codeasylum.themovietestproject.App
import javax.inject.Inject

class AllMoviesViewModel @Inject constructor(app : App) : ListViewModel(app) {

    override fun fetchData() {

    }
}