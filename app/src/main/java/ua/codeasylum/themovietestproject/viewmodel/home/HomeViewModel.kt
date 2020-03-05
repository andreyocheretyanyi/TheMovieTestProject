package ua.codeasylum.themovietestproject.viewmodel.home

import androidx.lifecycle.AndroidViewModel
import ua.codeasylum.themovietestproject.App
import javax.inject.Inject

class HomeViewModel @Inject constructor(app : App) : AndroidViewModel(app) {
    val fragmentNames = listOf("All", "Top Rated","Upcoming")
}