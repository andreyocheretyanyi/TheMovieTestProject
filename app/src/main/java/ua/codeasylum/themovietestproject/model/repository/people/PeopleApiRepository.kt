package ua.codeasylum.themovietestproject.model.repository.people

import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import ua.codeasylum.themovietestproject.BuildConfig
import ua.codeasylum.themovietestproject.base.service.PeopleService
import ua.codeasylum.themovietestproject.di.scope.ApplicationScope
import ua.codeasylum.themovietestproject.model.networkDto.PeopleDto
import javax.inject.Inject

@ApplicationScope
class PeopleApiRepository @Inject constructor(private val peopleService: PeopleService) :
    PeopleRepository {

    override fun searchPeople(query: String, page: Int): Single<PeopleDto> = peopleService
        .getPeopleByName(BuildConfig.API_KEY, query, page, true)
        .subscribeOn(Schedulers.io())


}