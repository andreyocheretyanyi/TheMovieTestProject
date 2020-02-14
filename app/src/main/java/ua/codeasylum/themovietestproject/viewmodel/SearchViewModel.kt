package ua.codeasylum.themovietestproject.viewmodel

import androidx.lifecycle.*
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import ua.codeasylum.themovietestproject.App
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
    val showDialog: MutableLiveData<Boolean> = MutableLiveData(false)


    fun onSearchClick() {

    }

    fun showDialog() {
        disposable.add(genreManager.fetchGenres()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { t1, _ ->
                allGenres.value = t1.genres.toMutableList()
                showDialog.value = true
            })

    }

    fun onGenresSelected() {
        selectedGenres.value?.apply {
            var genresQuery = ""
            var genresText = ""
            for (i in 0 until size) {
                when {
                    i != 0 -> {
                        genresQuery += ",${get(i).id}"
                        genresText += ",${get(i).name}"
                    }
                    else -> {
                        genresQuery += get(i).id
                        genresText += get(i).name
                    }
                }
            }
            this@SearchViewModel.genresText.value = genresText
            this@SearchViewModel.genresQuery.value = genresQuery
        }
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    fun clearResources(){
        disposable.dispose()
        disposable.clear()
    }
}

