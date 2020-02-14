package ua.codeasylum.themovietestproject.model.repository.people

import io.reactivex.Single
import ua.codeasylum.themovietestproject.di.scope.ApplicationScope
import ua.codeasylum.themovietestproject.model.networkDto.PeopleDto
import javax.inject.Inject

@ApplicationScope
class PeopleCacheRepository @Inject constructor() : PeopleRepository {
    override fun searchPeople(query: String, page: Int): Single<PeopleDto> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}