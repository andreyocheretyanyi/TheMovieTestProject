package ua.codeasylum.themovietestproject.base.service

import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query
import ua.codeasylum.themovietestproject.model.networkDto.PeopleDto

interface PeopleService {

    @GET("search/person")
    fun getPeopleByName(
        @Query("api_key") apiKey: String, @Query(
            "query",
            encoded = true
        ) query: String, @Query("page") page: Int,
        @Query("include_adult") includeAdult: Boolean
    ) : Single<PeopleDto>
}