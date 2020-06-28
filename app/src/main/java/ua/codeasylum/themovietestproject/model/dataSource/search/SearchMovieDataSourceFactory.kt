package ua.codeasylum.themovietestproject.model.dataSource.search

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import ua.codeasylum.themovietestproject.model.dataSource.MovieDataSourceFactoryBase
import ua.codeasylum.themovietestproject.model.networkDto.MovieResult

class SearchMovieDataSourceFactory(
    private val searchMovieDataSource: SearchMovieDataSource
) : MovieDataSourceFactoryBase() {



    fun updateArgs(
        year: String,
        movieQuery: String,
        genresIds: String,
        personId: String,
        isAdult: Boolean
    ) {
        searchMovieDataSource.updateArgs(year, movieQuery, genresIds, personId, isAdult)
    }


    override fun create(): DataSource<Int, MovieResult> = searchMovieDataSource

    override fun passErrorLiveData(mutableLiveData: MutableLiveData<Error>) {
        searchMovieDataSource.passErrorLiveData(mutableLiveData)
    }


}
