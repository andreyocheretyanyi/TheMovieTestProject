package ua.codeasylum.themovietestproject.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import ua.codeasylum.themovietestproject.App

abstract class ListViewModel(app : App) : AndroidViewModel(app) {

    abstract fun fetchData()
}