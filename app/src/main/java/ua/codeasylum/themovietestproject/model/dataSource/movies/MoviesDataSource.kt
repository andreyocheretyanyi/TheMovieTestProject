package ua.codeasylum.themovietestproject.model.dataSource.movies

import androidx.lifecycle.MutableLiveData
import androidx.paging.ItemKeyedDataSource
import ua.codeasylum.themovietestproject.model.networkDto.MovieResult
import ua.codeasylum.themovietestproject.model.repository.manager.MovieManager

class MoviesDataSource(
    val type: RequestType,
    private val movieManager: MovieManager,
    private val errorLiveData: MutableLiveData<String>
) : ItemKeyedDataSource<Int, MovieResult>() {

    enum class RequestType {
        TopRated, Upcoming
    }

    private var isEnd = false

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
        if (!isEnd)
            try {
                callback.onResult(
                    if (type == RequestType.TopRated)
                        movieManager.fetchTopRated(page).blockingGet()
                    else
                        movieManager.fetchUpcoming(page).blockingGet()

                )
            } catch (e: Exception) {
                errorLiveData.postValue(e.message)
            }
    }
}