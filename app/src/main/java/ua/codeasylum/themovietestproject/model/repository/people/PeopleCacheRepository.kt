package ua.codeasylum.themovietestproject.model.repository.people

import io.reactivex.Single
import ua.codeasylum.themovietestproject.base.DataPair
import ua.codeasylum.themovietestproject.di.scope.ApplicationScope
import ua.codeasylum.themovietestproject.model.networkDto.PeopleDto
import ua.codeasylum.themovietestproject.model.repository.SaveToCacheWithKey
import javax.inject.Inject

@ApplicationScope
class PeopleCacheRepository @Inject constructor() : PeopleRepository,
    SaveToCacheWithKey<DataPair<String, Int>, PeopleDto> {

    private val peopleMap = HashMap<DataPair<String, Int>, PeopleDto>()

    override fun searchPeople(query: String, page: Int): Single<PeopleDto> =
        DataPair(query, page).let {
            Single.just(peopleMap[it] ?: PeopleDto())
        }


    override fun save(key: DataPair<String, Int>, value: PeopleDto) {
        with(peopleMap) {
            if (containsKey(key))
                remove(key)
            peopleMap.put(key, value)
        }
    }

}

