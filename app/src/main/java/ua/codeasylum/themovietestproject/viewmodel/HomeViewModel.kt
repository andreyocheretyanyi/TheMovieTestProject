package ua.codeasylum.themovietestproject.viewmodel

import androidx.lifecycle.AndroidViewModel
import ua.codeasylum.themovietestproject.App
import javax.inject.Inject

class HomeViewModel @Inject constructor(app : App) : AndroidViewModel(app) {
    val fragmentNames = listOf<String>("All", "Top 100","Newest")
}