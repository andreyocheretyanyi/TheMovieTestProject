package ua.codeasylum.themovietestproject.model.repository

import ua.codeasylum.themovietestproject.model.repository.people.PeopleApiRepository
import ua.codeasylum.themovietestproject.model.repository.people.PeopleCacheRepository

class PeopleManager constructor(
    private val peopleApiRepository: PeopleApiRepository,
    private val peopleCacheRepository: PeopleCacheRepository
) : PeopleManagerInterface {

}