package ua.codeasylum.themovietestproject.di.module

import androidx.lifecycle.ViewModel

import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import ua.codeasylum.themovietestproject.di.scope.ViewModelKey
import ua.codeasylum.themovietestproject.model.SearchViewModel

@Module
abstract class ViewModelModule {



    @Binds
    @IntoMap
    @ViewModelKey(SearchViewModel::class)
    abstract fun mainScreenFragmentViewModel(SearchViewModel: SearchViewModel): ViewModel
}