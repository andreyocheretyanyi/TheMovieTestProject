package ua.codeasylum.themovietestproject.model.dataSource

import androidx.lifecycle.MutableLiveData

interface MovieDataSourceFactoryBase {

    fun passErrorLiveData(mutableLiveData: MutableLiveData<String>)

}