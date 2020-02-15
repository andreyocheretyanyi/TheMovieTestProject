package ua.codeasylum.themovietestproject.model.dataSource

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import ua.codeasylum.themovietestproject.model.networkDto.PeopleResult
import ua.codeasylum.themovietestproject.model.repository.PeopleManagerInterface

class PeopleDataSourceFactory(private val peopleManager: PeopleManagerInterface, name: String) :
    DataSource.Factory<Int, PeopleResult>() {

    private lateinit var peopleDataSource: PeopleDataSource
    var name = name
        set(value) {
            if (::peopleDataSource.isInitialized)
                peopleDataSource.name = value

            field = value
        }

    val dataSourceLiveData = MutableLiveData<PeopleDataSource>()

    override fun create(): DataSource<Int, PeopleResult> {
        val dataSource = PeopleDataSource(peopleManager, name)
        dataSourceLiveData.postValue(dataSource)
        return dataSource
    }
}