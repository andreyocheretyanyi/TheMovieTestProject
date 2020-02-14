package ua.codeasylum.themovietestproject.model.repository.people

import ua.codeasylum.themovietestproject.di.scope.ApplicationScope
import javax.inject.Inject

@ApplicationScope
class PeopleCacheRepository @Inject constructor() : PeopleRepository {
}