package ua.codeasylum.themovietestproject.model.repository.manager

import io.reactivex.Single
import ua.codeasylum.themovietestproject.base.DataPair
import ua.codeasylum.themovietestproject.model.networkDto.PeopleDto
import ua.codeasylum.themovietestproject.model.networkDto.Person
import ua.codeasylum.themovietestproject.model.repository.people.PeopleApiRepository
import ua.codeasylum.themovietestproject.model.repository.people.PeopleCacheRepository

class PeopleManager constructor(
    private val peopleApiRepository: PeopleApiRepository,
    private val peopleCacheRepository: PeopleCacheRepository
) : PeopleManagerInterface {

    override fun searchPeople(query: String, page: Int): Single<MutableList<Person>> =
        peopleCacheRepository.searchPeople(query, page)
            .flatMap { cachedDto ->
                if (cachedDto.results.isNotEmpty()) Single.just(cachedDto)
                else
                    peopleApiRepository.searchPeople(query, page)
                        .map {
                            peopleCacheRepository.save(DataPair(query, page), it)
                            return@map it
                        }
            }
            .flatMap { convertDtoToList(it) }


    private fun convertDtoToList(peopleDto: PeopleDto): Single<MutableList<Person>> {
        val list = mutableListOf<Person>()
        for (result in peopleDto.results) {
            result.page = peopleDto.page
            result.totalPages = peopleDto.totalPages
            list.add(result)
        }
        return Single.just(list)

    }
}