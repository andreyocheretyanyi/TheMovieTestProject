package ua.codeasylum.themovietestproject.model.repository

import io.reactivex.Single
import ua.codeasylum.themovietestproject.model.networkDto.PeopleDto

interface PeopleManagerInterface {

    fun searchPeople(query: String, page : Int) : Single<PeopleDto>
}