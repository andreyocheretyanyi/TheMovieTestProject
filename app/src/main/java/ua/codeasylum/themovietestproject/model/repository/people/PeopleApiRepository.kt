package ua.codeasylum.themovietestproject.model.repository.people

import ua.codeasylum.themovietestproject.base.service.PeopleService
import ua.codeasylum.themovietestproject.di.scope.ApplicationScope
import javax.inject.Inject

@ApplicationScope
class PeopleApiRepository @Inject constructor(private val peopleService: PeopleService) : PeopleRepository {
}