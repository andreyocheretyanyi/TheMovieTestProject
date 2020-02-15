package ua.codeasylum.themovietestproject.viewmodel

import android.util.Log
import androidx.lifecycle.*
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.subjects.PublishSubject
import ua.codeasylum.themovietestproject.App
import ua.codeasylum.themovietestproject.base.notifyObserver
import ua.codeasylum.themovietestproject.model.dataSource.PeopleDataSourceFactory
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
    private lateinit var peoplerDataSourceFactory: PeopleDataSourceFactory


    val query: MutableLiveData<String> = MutableLiveData("")
    val year: MutableLiveData<String> = MutableLiveData("")
    val selectedGenres: MutableLiveData<MutableList<Genre>> = MutableLiveData(mutableListOf())
    val genresText: MutableLiveData<String> = MutableLiveData()
    val allGenres: MutableLiveData<MutableList<Genre>> = MutableLiveData(mutableListOf())
    val openSelectGenre: MutableLiveData<Boolean> = MutableLiveData(false)
    val openPersonSearch: MutableLiveData<Boolean> = MutableLiveData(false)
    var foundPeople: LiveData<PagedList<PeopleResult>> = MutableLiveData()
    val enteredPersonName: MutableLiveData<String> = MutableLiveData("")
    val haveToNotifyPeopleBindingAdapter = MutableLiveData(1)


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
        peoplerDataSourceFactory = PeopleDataSourceFactory(peopleManager, "")
        peopleDisposable = searchPublishSubject
            .debounce(500, TimeUnit.MILLISECONDS)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                initFactory(it)
            }, {
                Log.d("", it.toString())
            })

    }

    private fun initFactory(name: String) {
        if (::peoplerDataSourceFactory.isInitialized)
            peoplerDataSourceFactory.name = name
        else
            peoplerDataSourceFactory = PeopleDataSourceFactory(peopleManager, name)

        val config = PagedList.Config.Builder()
            .setPageSize(20)
            .setEnablePlaceholders(false)
            .build()
        foundPeople =
            LivePagedListBuilder<Int, PeopleResult>(peoplerDataSourceFactory, config).build()
        haveToNotifyPeopleBindingAdapter.notifyObserver()

    }

}

