package ua.codeasylum.themovietestproject.model.repository.people

import io.reactivex.Single
import ua.codeasylum.themovietestproject.di.scope.ApplicationScope
import ua.codeasylum.themovietestproject.model.networkDto.PeopleDto
import ua.codeasylum.themovietestproject.model.repository.SaveToCacheWithKey
import javax.inject.Inject

@ApplicationScope
class PeopleCacheRepository @Inject constructor() : PeopleRepository,
    SaveToCacheWithKey<Pair<String, Int>, PeopleDto> {

    private val peopleMap = HashMap<Pair<String, Int>, PeopleDto>()

    override fun searchPeople(query: String, page: Int): Single<PeopleDto> =
        Pair(query, page).let {
            Single.just(peopleMap[it] ?: PeopleDto())
        }


    override fun save(key: Pair<String, Int>, value: PeopleDto) {
        with(peopleMap) {
            if (containsKey(key))
                remove(key)
            peopleMap.put(key, value)
        }
    }

}

