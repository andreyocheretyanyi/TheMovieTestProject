package ua.codeasylum.themovietestproject.model.dataSource

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import ua.codeasylum.themovietestproject.model.networkDto.MovieResult
import ua.codeasylum.themovietestproject.model.networkDto.Person
import ua.codeasylum.themovietestproject.model.repository.manager.MovieManagerInterface
import java.time.Year

class MovieDataSourceFactory(
    private val movieManager: MovieManagerInterface,
    private val year: String,
    private val movieQuery: String,
    private val genresIds: String,
    private val personId: String,
    private val isAdult: Boolean
) : DataSource.Factory<Int, MovieResult>() {


    private val dataSourceLiveData = MutableLiveData<MovieDataSource>()

    override fun create(): DataSource<Int, MovieResult> =
        MovieDataSource(movieManager, year, movieQuery, genresIds, personId, isAdult).apply {
            dataSourceLiveData.postValue(this)
        }

}
