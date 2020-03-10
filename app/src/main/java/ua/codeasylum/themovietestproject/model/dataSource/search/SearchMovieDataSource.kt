package ua.codeasylum.themovietestproject.model.dataSource.search

import androidx.lifecycle.MutableLiveData
import androidx.paging.ItemKeyedDataSource
import ua.codeasylum.themovietestproject.model.networkDto.MovieResult
import ua.codeasylum.themovietestproject.model.repository.manager.MovieManagerInterface
import ua.codeasylum.themovietestproject.model.repository.movie.MovieArgs

class SearchMovieDataSource(
    private val movieManager: MovieManagerInterface
) : ItemKeyedDataSource<Int, MovieResult>() {

    private var isEnd = false
    private var argsIsInit = false
    private lateinit var year: String
    private lateinit var movieQuery: String
    private lateinit var genresIds: String
    private lateinit var personId: String
    private var isAdult: Boolean = false
    private lateinit var errorLiveData: MutableLiveData<String>

    fun updateArgs(
        year: String,
        movieQuery: String,
        genresIds: String,
        personId: String,
        isAdult: Boolean
    ) {
        this.year = year
        this.movieQuery = movieQuery
        this.genresIds = genresIds
        this.personId = personId
        this.isAdult = isAdult
        argsIsInit = true
    }

    override fun loadInitial(
        params: LoadInitialParams<Int>,
        callback: LoadInitialCallback<MovieResult>
    ) {
        makeRequest(callback, 1)
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<MovieResult>) {
        makeRequest(callback, params.key)
    }

    override fun getKey(item: MovieResult): Int {
        isEnd = item.page == item.totalPages
        return if (item.page < item.totalPages) 1 + item.page else item.page
    }


    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<MovieResult>) {

    }

    private fun makeRequest(callback: LoadCallback<MovieResult>, page: Int) {
        if (!isEnd && argsIsInit)
            try {
                callback.onResult(
                    movieManager.fetchMoviesByAgruments(
                        MovieArgs(
                            movieQuery,
                            isAdult,
                            page,
                            if (year.isNotEmpty()) year.toInt() else null,
                            genresIds,
                            personId
                        )
                    ).blockingGet()
                )
            } catch (e: Exception) {
                if (::errorLiveData.isInitialized)
                    errorLiveData.postValue(e.message)
            }
    }

    fun passErrorLiveData(mutableLiveData: MutableLiveData<String>) {
        errorLiveData = mutableLiveData
    }
}