package ua.codeasylum.themovietestproject.model.repository.genre

import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import ua.codeasylum.themovietestproject.BuildConfig
import ua.codeasylum.themovietestproject.base.service.GenreService
import ua.codeasylum.themovietestproject.di.scope.ApplicationScope
import ua.codeasylum.themovietestproject.model.networkDto.GenreDto
import javax.inject.Inject

@ApplicationScope
class GenreApiRepository @Inject constructor(private val genreService: GenreService) :
    GenreRepository {

    override fun getGenres(): Single<GenreDto> =
        genreService.getGenres(BuildConfig.API_KEY).subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())


}