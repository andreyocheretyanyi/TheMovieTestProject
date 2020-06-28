package ua.codeasylum.themovietestproject.model.dataSource.movies

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import ua.codeasylum.themovietestproject.model.dataSource.MovieDataSourceFactoryBase
import ua.codeasylum.themovietestproject.model.networkDto.MovieResult

class MoviesDataSourceFactory(
    val type: MoviesDataSource.RequestType,
    private val movieDataSource : MoviesDataSource
) : MovieDataSourceFactoryBase() {

    override fun create(): DataSource<Int, MovieResult> = movieDataSource

    override fun passErrorLiveData(mutableLiveData: MutableLiveData<Error>) {
        movieDataSource.passErrorLiveData(mutableLiveData)
    }

}