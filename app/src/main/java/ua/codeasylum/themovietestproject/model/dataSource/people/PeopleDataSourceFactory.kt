package ua.codeasylum.themovietestproject.model.dataSource.people

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import ua.codeasylum.themovietestproject.model.networkDto.Person
import ua.codeasylum.themovietestproject.model.repository.manager.PeopleManagerInterface

class PeopleDataSourceFactory(private val peopleManager: PeopleManagerInterface,private val errorLiveData : MutableLiveData<String>,
                              name: String) :
    DataSource.Factory<Int, Person>() {

    private lateinit var peopleDataSource: PeopleDataSource
    var name = name
        set(value) {
            if (::peopleDataSource.isInitialized)
                peopleDataSource.name = value

            field = value
        }

    private val dataSourceLiveData = MutableLiveData<PeopleDataSource>()

    override fun create(): DataSource<Int, Person> {
        peopleDataSource =
            PeopleDataSource(
                peopleManager,
                name,
                errorLiveData
            )
        dataSourceLiveData.postValue(peopleDataSource)
        return peopleDataSource
    }
}