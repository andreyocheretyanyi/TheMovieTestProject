package ua.codeasylum.themovietestproject.model.repository

import io.reactivex.Single
import ua.codeasylum.themovietestproject.model.networkDto.PeopleDto
import ua.codeasylum.themovietestproject.model.repository.people.PeopleApiRepository
import ua.codeasylum.themovietestproject.model.repository.people.PeopleCacheRepository

class PeopleManager constructor(
    private val peopleApiRepository: PeopleApiRepository,
    private val peopleCacheRepository: PeopleCacheRepository
) : PeopleManagerInterface {

    override fun searchPeople(query: String, page: Int): Single<PeopleDto> =
        peopleApiRepository.searchPeople(query, page)


}