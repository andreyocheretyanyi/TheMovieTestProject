package ua.codeasylum.themovietestproject.model.dataSource

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import ua.codeasylum.themovietestproject.model.networkDto.MovieResult

abstract class MovieDataSourceFactoryBase : DataSource.Factory<Int, MovieResult>() {

     abstract fun passErrorLiveData(mutableLiveData: MutableLiveData<String>)

}