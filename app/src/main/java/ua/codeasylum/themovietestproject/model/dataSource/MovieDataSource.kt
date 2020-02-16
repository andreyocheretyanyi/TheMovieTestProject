package ua.codeasylum.themovietestproject.model.dataSource

import androidx.lifecycle.MutableLiveData
import androidx.paging.ItemKeyedDataSource
import ua.codeasylum.themovietestproject.model.networkDto.MovieResult
import ua.codeasylum.themovietestproject.model.repository.manager.MovieManagerInterface
import ua.codeasylum.themovietestproject.model.repository.movie.MovieArgs
import java.lang.Exception

class MovieDataSource(
    private val movieManager: MovieManagerInterface,
    private val year: String,
    private val movieQuery: String,
    private val genresIds: String,
    private val personId: String,
    private val isAdult: Boolean,
    private val errorLiveData: MutableLiveData<String>
) : ItemKeyedDataSource<Int, MovieResult>() {
    override fun loadInitial(
        params: LoadInitialParams<Int>,
        callback: LoadInitialCallback<MovieResult>
    ) {
        makeRequest(callback, 1)
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<MovieResult>) {
        makeRequest(callback, params.key)
    }

    override fun getKey(item: MovieResult): Int =
        if (item.page <= item.totalPages) 1 + item.page else item.page


    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<MovieResult>) {

    }

    private fun makeRequest(callback: LoadCallback<MovieResult>, page: Int) {
        try {
            callback.onResult(
                movieManager.fetchMoviesByAgruments(
                    MovieArgs(movieQuery,isAdult,page,if (year.isNotEmpty()) year.toInt() else null,genresIds,personId)
                ).blockingGet()
            )
        } catch (e: Exception) {
            errorLiveData.postValue(e.message)
        }
    }
}