package ua.codeasylum.themovietestproject.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import ua.codeasylum.themovietestproject.di.scope.ApplicationScope
import javax.inject.Inject
import javax.inject.Provider

@ApplicationScope
class ViewModelFactory @Inject constructor(private val viewModels: MutableMap<Class<out ViewModel>, Provider<ViewModel>>) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        val viewModelProvider = viewModels[modelClass]
            ?: throw IllegalArgumentException("model class $modelClass not found")
        return viewModelProvider.get() as T
    }
}