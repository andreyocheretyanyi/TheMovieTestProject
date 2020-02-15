package ua.codeasylum.themovietestproject.model.dataSource

import androidx.paging.ItemKeyedDataSource
import ua.codeasylum.themovietestproject.model.networkDto.PeopleResult
import ua.codeasylum.themovietestproject.model.repository.PeopleManagerInterface
import java.lang.Exception

class PeopleDataSource(
    private val peopleManager: PeopleManagerInterface,
    var name: String

) : ItemKeyedDataSource<Int, PeopleResult>() {
    override fun loadInitial(
        params: LoadInitialParams<Int>,
        callback: LoadInitialCallback<PeopleResult>
    ) {
        if (name.isNotEmpty())
            try {

                callback.onResult(peopleManager.searchPeople(name, 1).blockingGet())
            } catch (e: Exception) {
                callback.onError(e)
            }

    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<PeopleResult>) {
        if (name.isNotEmpty())
            try {
                callback.onResult(peopleManager.searchPeople(name, params.key).blockingGet())
            } catch (e: Exception) {
                callback.onError(e)
            }


    }


    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<PeopleResult>) {

    }

    override fun getKey(item: PeopleResult): Int =
        if (item.page <= item.totalPages) 1 + item.page else item.page


}