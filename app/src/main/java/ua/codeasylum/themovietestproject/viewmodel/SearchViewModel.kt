package ua.codeasylum.themovietestproject.viewmodel

import android.util.Log
import androidx.lifecycle.*
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.PublishSubject
import ua.codeasylum.themovietestproject.App
import ua.codeasylum.themovietestproject.base.notifyObserver
import ua.codeasylum.themovietestproject.model.networkDto.Genre
import ua.codeasylum.themovietestproject.model.networkDto.PeopleResult
import ua.codeasylum.themovietestproject.model.repository.GenreManagerInterface
import ua.codeasylum.themovietestproject.model.repository.PeopleManagerInterface
import java.util.concurrent.TimeUnit
import javax.inject.Inject


class SearchViewModel @Inject constructor(
    app: App,
    private val genreManager: GenreManagerInterface,
    private val peopleManager: PeopleManagerInterface
) : AndroidViewModel(app) {


    val enteredPersonNameObserver: Observer<String> by lazy {
        observeEnteredPersonName()
    }
    private lateinit var genresDisposable: Disposable
    private lateinit var peopleDisposable: Disposable
    private val genresQuery: MutableLiveData<String> = MutableLiveData()
    private val searchPublishSubject by lazy {
        PublishSubject.create<String>()
    }
    val query: MutableLiveData<String> = MutableLiveData("")
    val year: MutableLiveData<String> = MutableLiveData("")
    val selectedGenres: MutableLiveData<MutableList<Genre>> = MutableLiveData(mutableListOf())
    val genresText: MutableLiveData<String> = MutableLiveData()
    val allGenres: MutableLiveData<MutableList<Genre>> = MutableLiveData(mutableListOf())
    val openSelectGenre: MutableLiveData<Boolean> = MutableLiveData(false)
    val openPersonSearch: MutableLiveData<Boolean> = MutableLiveData(false)
    val foundPeople: MutableLiveData<MutableList<PeopleResult>> = MutableLiveData(mutableListOf())
    val enteredPersonName: MutableLiveData<String> = MutableLiveData("")


    fun onSearchClick() {

    }

    fun openPersonSearch() {
        openPersonSearch.value = true

    }

    fun openSelectGenre() {
        openSelectGenre.value = true
    }

    fun fetchGenres() {
        if (::genresDisposable.isInitialized && !genresDisposable.isDisposed)
            genresDisposable.dispose()

        genresDisposable = genreManager.fetchGenres()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { t1, _ ->
                allGenres.value?.clear()
                allGenres.value?.addAll(t1.genres)
                allGenres.notifyObserver()
            }


    }

    private fun observeEnteredPersonName(): Observer<String> = Observer {
        val name = enteredPersonName.value ?: ""
        if (name.isNotEmpty())
            searchPublishSubject
                .onNext(name)

    }


    fun onSelectSingleGenre(genre: Genre) {
        selectedGenres.value?.add(genre)
        appendGenreData(genre)
        selectedGenres.notifyObserver()
    }

    fun clearGenresClick() {
        genresText.value = ""
        genresQuery.value = ""
        selectedGenres.value?.clear()
        selectedGenres.notifyObserver()
    }


    private fun appendGenreData(genre: Genre) {
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

    fun subscribeTextChange() {
        if (::peopleDisposable.isInitialized && !peopleDisposable.isDisposed)
            peopleDisposable.dispose()

        peopleDisposable = searchPublishSubject
            .debounce(500, TimeUnit.MILLISECONDS)
            .subscribeOn(Schedulers.io())
            .concatMap { peopleManager.searchPeople(it, 1).toObservable() }
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                foundPeople.value?.clear()
                foundPeople.value?.addAll(it.results)
                foundPeople.notifyObserver()
            }, {
                Log.d("", it.toString())
            })

    }
}

