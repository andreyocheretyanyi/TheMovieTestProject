package ua.codeasylum.themovietestproject.model.dataSource.people

import androidx.lifecycle.MutableLiveData
import androidx.paging.ItemKeyedDataSource
import ua.codeasylum.themovietestproject.model.networkDto.Person
import ua.codeasylum.themovietestproject.model.repository.manager.PeopleManagerInterface

class PeopleDataSource(
    private val peopleManager: PeopleManagerInterface
) : ItemKeyedDataSource<Int, Person>() {

    private var isEnd = false
    private lateinit var errorLiveData: MutableLiveData<String>
    private var name: String = ""

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

    override fun getKey(item: Person): Int {
        isEnd = item.page == item.totalPages
        return if (item.page < item.totalPages) 1 + item.page else item.page
    }


    private fun makeRequest(callback: LoadCallback<Person>, page: Int) {
        if (name.isNotEmpty() && !isEnd)
            try {

                callback.onResult(peopleManager.searchPeople(name, page).blockingGet())
            } catch (e: Exception) {
                if (::errorLiveData.isInitialized)
                    errorLiveData.postValue(e.message)
            }
    }

    fun updateName(name: String) {
        this.name = name
        isEnd = false
    }

    fun passErrorLiveData(liveData: MutableLiveData<String>) {
        errorLiveData = liveData
    }


}