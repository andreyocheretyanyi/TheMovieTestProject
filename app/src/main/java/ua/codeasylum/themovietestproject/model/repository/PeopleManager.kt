package ua.codeasylum.themovietestproject.model.repository

import io.reactivex.Single
import ua.codeasylum.themovietestproject.model.networkDto.PeopleDto
import ua.codeasylum.themovietestproject.model.networkDto.Person
import ua.codeasylum.themovietestproject.model.repository.people.PeopleApiRepository

class PeopleManager constructor(
    private val peopleApiRepository: PeopleApiRepository
) : PeopleManagerInterface {

    override fun searchPeople(query: String, page: Int): Single<MutableList<Person>> =
        peopleApiRepository.searchPeople(query, page)
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