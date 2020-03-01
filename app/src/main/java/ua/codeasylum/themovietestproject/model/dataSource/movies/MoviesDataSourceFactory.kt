package ua.codeasylum.themovietestproject.model.dataSource.movies

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import ua.codeasylum.themovietestproject.model.networkDto.MovieResult
import ua.codeasylum.themovietestproject.model.repository.manager.MovieManager

class MoviesDataSourceFactory(
    val type: MoviesDataSource.RequestType,
    private val movieManager: MovieManager,
    private val errorLiveData: MutableLiveData<String>
) : DataSource.Factory<Int, MovieResult>() {
    override fun create(): DataSource<Int, MovieResult> = MoviesDataSource(type,movieManager,errorLiveData)

}