package ua.codeasylum.themovietestproject.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.navigation.NavController
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import ua.codeasylum.themovietestproject.App
import ua.codeasylum.themovietestproject.R
import ua.codeasylum.themovietestproject.base.debounce
import ua.codeasylum.themovietestproject.base.notifyObserver
import ua.codeasylum.themovietestproject.model.dataSource.people.PeopleDataSourceFactory
import ua.codeasylum.themovietestproject.model.networkDto.Genre
import ua.codeasylum.themovietestproject.model.networkDto.Person
import ua.codeasylum.themovietestproject.model.repository.manager.GenreManagerInterface
import ua.codeasylum.themovietestproject.view.search.SearchFragmentDirections
import javax.inject.Inject


class SearchViewModel @Inject constructor(
    app: App,
    private val genreManager: GenreManagerInterface,
    private val navController: NavController,
    private val peopleDataSourceFactory: PeopleDataSourceFactory
) : BaseViewModel(app) {

    val enteredPersonNameObserver: Observer<String> by lazy {
        observeEnteredPersonName()
    }
    private lateinit var genresDisposable: Disposable

    private val genresQuery by lazy {
        MutableLiveData("")
    }
    private val personQuery by lazy {
        MutableLiveData("")
    }

    private val config: PagedList.Config = PagedList.Config.Builder()
        .setPageSize(20)
        .setEnablePlaceholders(false)
        .build()


    val query = MutableLiveData("")
    val year = MutableLiveData("")
    val selectedPersonName = MutableLiveData("")
    val genresText = MutableLiveData("")
    val allGenres: MutableLiveData<MutableList<Genre>> = MutableLiveData(mutableListOf())
    val selectedGenres: MutableLiveData<MutableList<Genre>> = MutableLiveData(mutableListOf())
    var foundPeople: LiveData<PagedList<Person>> = MutableLiveData()
    val personSearchedName = MutableLiveData("").debounce(500L)
    val haveToNotifyPeopleBindingAdapter = MutableLiveData(1)
    val isAdult = MutableLiveData(false)

    init {
        peopleDataSourceFactory.passErrorLiveData(error)
    }


    fun onSearchClick() {
        val year = year.value ?: ""
        val movieQuery = query.value ?: ""
        val genresId = genresQuery.value ?: ""
        val personId = personQuery.value ?: ""
        val isAdult = isAdult.value ?: false

        if (year.isNotEmpty() || movieQuery.isNotEmpty() || genresId.isNotEmpty() || personId.isNotEmpty())
            navController.navigate(
                SearchFragmentDirections.actionSearchFragmentToSearchResultFragment(
                    year, movieQuery, genresId, personId, isAdult
                )
            ) else {
            error.value =
                Error(getApplication<App>().getString(R.string.fill_in_at_least_one_field))

        }
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
            .subscribe({
                allGenres.value?.clear()
                allGenres.value?.addAll(it.genres)
                allGenres.notifyObserver()
            }, {
                error.value = Error(it)
            })


    }

    private fun observeEnteredPersonName(): Observer<String> = Observer {
        try {
            updateDataSourceFactoryAndSearchQuery(personSearchedName.value ?: "")
        } catch (e: Exception) {
            error.value = Error(e)
        }


    }


    fun onSelectSingleGenre(genre: Genre, selected: Boolean) {
        if (!selected) {
            selectedGenres.value?.add(genre)
            appendGenreData(genre)
            selectedGenres.notifyObserver()
        }
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
                ", ${genre.name}"
            else
                genre.name

            genresQuery += if (genresQuery.isNotEmpty())
                "|${genre.id}"
            else
                genre.id


            this@SearchViewModel.genresQuery.value = genresQuery
            this@SearchViewModel.genresText.value = genresText

        }
    }


    private fun updateDataSourceFactoryAndSearchQuery(name: String) {
        peopleDataSourceFactory.name = name
        foundPeople = LivePagedListBuilder<Int, Person>(peopleDataSourceFactory, config).build()
        haveToNotifyPeopleBindingAdapter.notifyObserver()
    }

    fun personSelected(person: Person) {
        selectedPersonName.value = person.name
        personQuery.value = person.id.toString()
        navController.navigateUp()
    }

}

