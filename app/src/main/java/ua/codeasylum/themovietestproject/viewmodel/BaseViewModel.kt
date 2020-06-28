package ua.codeasylum.themovietestproject.viewmodel

import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import ua.codeasylum.themovietestproject.App

open class BaseViewModel(app: App) : AndroidViewModel(app) {
    val error = MutableLiveData<Error>()
}