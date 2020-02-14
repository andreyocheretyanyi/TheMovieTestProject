package ua.codeasylum.themovietestproject.model.repository.people

import io.reactivex.Single
import ua.codeasylum.themovietestproject.model.networkDto.PeopleDto

interface PeopleRepository {

    fun searchPeople(query: String, page: Int): Single<PeopleDto>
}