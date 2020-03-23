package ua.codeasylum.themovietestproject.model.dataSource.people

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import ua.codeasylum.themovietestproject.model.networkDto.Person

class PeopleDataSourceFactory(
    private val peopleDataSource: PeopleDataSource
) :
    DataSource.Factory<Int, Person>() {

    var name = ""
        set(value) {
            peopleDataSource.updateName(value)
            field = value
        }

    override fun create(): DataSource<Int, Person> = peopleDataSource

    fun passErrorLiveData(liveData: MutableLiveData<String>) {
        peopleDataSource.passErrorLiveData(liveData)
    }
}