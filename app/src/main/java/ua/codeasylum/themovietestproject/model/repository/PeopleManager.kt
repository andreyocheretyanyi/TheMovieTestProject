package ua.codeasylum.themovietestproject.model.repository

import ua.codeasylum.themovietestproject.model.repository.people.PeopleApiRepository

class PeopleManager constructor(private val peopleApiRepository: PeopleApiRepository) : PeopleManagerInterface {
}