package ua.codeasylum.themovietestproject.di.module

import androidx.lifecycle.ViewModel

import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import ua.codeasylum.themovietestproject.di.scope.ViewModelKey
import ua.codeasylum.themovietestproject.viewmodel.*

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

    @Binds
    @IntoMap
    @ViewModelKey(HomeViewModel::class)
    abstract fun homeViewModel(homeViewModel : HomeViewModel) : ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(AllMoviesViewModel::class)
    abstract fun allMoviesViewModel(allMoviesViewModel : AllMoviesViewModel) : ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(TopMoviesViewModel::class)
    abstract fun topMoviesViewModel(topMoviesViewModel : TopMoviesViewModel) : ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(NewestMovieViewModel::class)
    abstract fun newestMoviesViewModel(newestMovieViewModel : NewestMovieViewModel) : ViewModel
}