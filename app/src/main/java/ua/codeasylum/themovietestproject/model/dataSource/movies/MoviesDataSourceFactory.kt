package ua.codeasylum.themovietestproject.model.dataSource.movies

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import ua.codeasylum.themovietestproject.model.dataSource.MovieDataSourceFactoryBase
import ua.codeasylum.themovietestproject.model.networkDto.MovieResult
import ua.codeasylum.themovietestproject.model.repository.manager.MovieManagerInterface

class MoviesDataSourceFactory(
    val type: MoviesDataSource.RequestType,
    private val movieDataSource : MoviesDataSource
) : DataSource.Factory<Int, MovieResult>(), MovieDataSourceFactoryBase {

    override fun create(): DataSource<Int, MovieResult> = movieDataSource

    override fun passErrorLiveData(mutableLiveData: MutableLiveData<String>) {
        movieDataSource.passErrorLiveData(mutableLiveData)
    }

}