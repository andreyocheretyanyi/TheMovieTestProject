package ua.codeasylum.themovietestproject.di.module

import androidx.lifecycle.ViewModel

import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import ua.codeasylum.themovietestproject.di.scope.ViewModelKey
import ua.codeasylum.themovietestproject.viewmodel.MovieDetailViewModel
import ua.codeasylum.themovietestproject.viewmodel.SearchResultViewModel
import ua.codeasylum.themovietestproject.viewmodel.SearchViewModel

@Module
abstract class ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(SearchViewModel::class)
    abstract fun searchViewModel(searchViewModel: SearchViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(SearchResultViewModel::class)
    abstract fun searchResultViewModel(searchResultViewModel: SearchResultViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(MovieDetailViewModel::class)
    abstract fun movieDetailViewModel(movieDetailViewModel: MovieDetailViewModel): ViewModel
}