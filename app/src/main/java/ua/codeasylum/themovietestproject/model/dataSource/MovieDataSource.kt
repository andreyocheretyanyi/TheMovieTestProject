package ua.codeasylum.themovietestproject.model.dataSource

import androidx.paging.ItemKeyedDataSource
import ua.codeasylum.themovietestproject.model.networkDto.MovieResult
import ua.codeasylum.themovietestproject.model.repository.manager.MovieManagerInterface
import java.lang.Exception

class MovieDataSource(
    val movieManager: MovieManagerInterface,
    val year: String,
    val movieQuery: String,
    val genresIds: String,
    val personId: String,
    val isAdult: Boolean
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
                    movieQuery, isAdult,
                    page,
                    if (year.isNotEmpty()) year.toInt() else null,
                    genresIds,
                    personId
                ).blockingGet()
            )
        } catch (e: Exception) {
            callback.onError(e)
        }
    }
}