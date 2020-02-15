package ua.codeasylum.themovietestproject.model.dataSource

import androidx.paging.ItemKeyedDataSource
import ua.codeasylum.themovietestproject.model.networkDto.Person
import ua.codeasylum.themovietestproject.model.repository.manager.PeopleManagerInterface
import java.lang.Exception

class PeopleDataSource(
    private val peopleManager: PeopleManagerInterface,
    var name: String

) : ItemKeyedDataSource<Int, Person>() {
    override fun loadInitial(
        params: LoadInitialParams<Int>,
        callback: LoadInitialCallback<Person>
    ) {
        makeRequest(callback, 1)
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Person>) {
        makeRequest(callback, params.key)
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Person>) {

    }

    override fun getKey(item: Person): Int =
        if (item.page <= item.totalPages) 1 + item.page else item.page


    private fun makeRequest(callback: LoadCallback<Person>, page: Int) {
        if (name.isNotEmpty())
            try {

                callback.onResult(peopleManager.searchPeople(name, page).blockingGet())
            } catch (e: Exception) {
                callback.onError(e)
            }
    }


}