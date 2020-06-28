package ua.codeasylum.themovietestproject.viewmodel.home

import ua.codeasylum.themovietestproject.App
import ua.codeasylum.themovietestproject.viewmodel.BaseViewModel
import javax.inject.Inject

class HomeViewModel @Inject constructor(app: App) : BaseViewModel(app) {
    val title: String = "Home"
    val fragmentNames = listOf("All", "Top Rated", "Upcoming")
}