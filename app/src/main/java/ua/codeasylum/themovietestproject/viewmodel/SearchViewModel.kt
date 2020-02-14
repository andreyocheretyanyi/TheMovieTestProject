package ua.codeasylum.themovietestproject.viewmodel

import androidx.lifecycle.*
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import ua.codeasylum.themovietestproject.App
import ua.codeasylum.themovietestproject.base.notifyObserver
import ua.codeasylum.themovietestproject.model.networkDto.Genre
import ua.codeasylum.themovietestproject.model.repository.GenreManagerInterface
import javax.inject.Inject

class SearchViewModel @Inject constructor(
    app: App,
    private val genreManager: GenreManagerInterface
) : AndroidViewModel(app), LifecycleObserver {


    private var disposable: CompositeDisposable = CompositeDisposable()
    val query: MutableLiveData<String> = MutableLiveData("")
    val year: MutableLiveData<String> = MutableLiveData("")
    val selectedGenres: MutableLiveData<MutableList<Genre>> = MutableLiveData(mutableListOf())
    val genresText: MutableLiveData<String> = MutableLiveData()
    private val genresQuery: MutableLiveData<String> = MutableLiveData()
    val allGenres: MutableLiveData<MutableList<Genre>> = MutableLiveData(mutableListOf())
    val openSelectGenre: MutableLiveData<Boolean> = MutableLiveData(false)
    val openPersonSearch: MutableLiveData<Boolean> = MutableLiveData(false)
    val personName: MutableLiveData<String> = MutableLiveData("")

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    fun start() {
        disposable = CompositeDisposable()
        fetchGenres()
    }

    fun onSearchClick() {

    }

    fun openPersonSearch() {
        openPersonSearch.value = true

    }

    fun openSelectGenre() {
        openSelectGenre.value = true
    }

    private fun fetchGenres() {
        disposable.add(genreManager.fetchGenres()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { t1, _ ->
                allGenres.value = t1.genres.toMutableList()
            })

    }

    fun onSelectSingleGenre(genre: Genre) {
        selectedGenres.value?.add(genre)
        appendData(genre)
        selectedGenres.notifyObserver()
    }

    fun clearGenresClick() {
        genresText.value = ""
        genresQuery.value = ""
        selectedGenres.value?.clear()
        selectedGenres.notifyObserver()
    }


    private fun appendData(genre: Genre) {
        selectedGenres.value?.apply {
            var genresQuery = this@SearchViewModel.genresQuery.value ?: ""
            var genresText = this@SearchViewModel.genresText.value ?: ""

            genresText += if (genresText.isNotEmpty())
                ",${genre.name}"
            else
                genre.name

            genresQuery += if (genresQuery.isNotEmpty())
                genre.id
            else
                ",${genre.id}"

            this@SearchViewModel.genresQuery.value = genresQuery
            this@SearchViewModel.genresText.value = genresText

        }
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    fun clearResources() {
        disposable.dispose()
        disposable.clear()
    }
}

