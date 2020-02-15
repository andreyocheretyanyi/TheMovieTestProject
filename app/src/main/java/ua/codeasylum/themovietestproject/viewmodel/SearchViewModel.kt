package ua.codeasylum.themovietestproject.viewmodel

import android.util.Log
import androidx.lifecycle.*
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.subjects.PublishSubject
import ua.codeasylum.themovietestproject.App
import ua.codeasylum.themovietestproject.R
import ua.codeasylum.themovietestproject.base.notifyObserver
import ua.codeasylum.themovietestproject.model.dataSource.PeopleDataSourceFactory
import ua.codeasylum.themovietestproject.model.networkDto.Genre
import ua.codeasylum.themovietestproject.model.networkDto.Person
import ua.codeasylum.themovietestproject.model.repository.manager.GenreManagerInterface
import ua.codeasylum.themovietestproject.model.repository.manager.PeopleManagerInterface
import java.util.concurrent.TimeUnit
import javax.inject.Inject


class SearchViewModel @Inject constructor(
    app: App,
    private val genreManager: GenreManagerInterface,
    private val peopleManager: PeopleManagerInterface,
    private val navController: NavController
) : AndroidViewModel(app) {


    val enteredPersonNameObserver: Observer<String> by lazy {
        observeEnteredPersonName()
    }
    private lateinit var genresDisposable: Disposable
    private lateinit var peopleDisposable: Disposable

    private val genresQuery by lazy {
        MutableLiveData("")
    }
    private val personQuery by lazy {
        MutableLiveData("")
    }
    private val searchPublishSubject by lazy {
        PublishSubject.create<String>()
    }
    private lateinit var peoplerDataSourceFactory: PeopleDataSourceFactory


    val query = MutableLiveData("")
    val year = MutableLiveData("")
    val selectedPersonName = MutableLiveData("")
    val openSelectGenre = MutableLiveData(false)
    val openPersonSearch = MutableLiveData(false)
    val genresText = MutableLiveData("")
    val allGenres: MutableLiveData<MutableList<Genre>> = MutableLiveData(mutableListOf())
    val selectedGenres: MutableLiveData<MutableList<Genre>> = MutableLiveData(mutableListOf())
    var foundPeople: LiveData<PagedList<Person>> = MutableLiveData()
    val personSearchedName = MutableLiveData("")
    val haveToNotifyPeopleBindingAdapter = MutableLiveData(1)
    val isAdult = MutableLiveData(false)


    fun onSearchClick() {

    }

    fun openPersonSearch() {
        navController.navigate(R.id.action_searchFragment_to_searchPeopleFragment)

    }

    fun openSelectGenre() {
        navController.navigate(R.id.action_searchFragment_to_selectGenresFragment)
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
        val name = personSearchedName.value ?: ""
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

    fun onButtonSelectGenreClick() {
        navController.navigateUp()
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

    fun subscribePersonSearchTextChange() {

        if (::peopleDisposable.isInitialized && !peopleDisposable.isDisposed)
            peopleDisposable.dispose()
        peoplerDataSourceFactory = PeopleDataSourceFactory(peopleManager, "")
        peopleDisposable = searchPublishSubject
            .debounce(500, TimeUnit.MILLISECONDS)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                initPersonDataSourceFactory(it)
            }, {
                Log.d("", it.toString())
            })

    }

    private fun initPersonDataSourceFactory(name: String) {
        if (::peoplerDataSourceFactory.isInitialized)
            peoplerDataSourceFactory.name = name
        else
            peoplerDataSourceFactory = PeopleDataSourceFactory(peopleManager, name)

        val config = PagedList.Config.Builder()
            .setPageSize(20)
            .setEnablePlaceholders(false)
            .build()
        foundPeople =
            LivePagedListBuilder<Int, Person>(peoplerDataSourceFactory, config).build()
        haveToNotifyPeopleBindingAdapter.notifyObserver()

    }

    fun personSelected(person: Person) {
        selectedPersonName.value = person.name
        personQuery.value = person.id.toString()
        navController.navigateUp()
    }

}

