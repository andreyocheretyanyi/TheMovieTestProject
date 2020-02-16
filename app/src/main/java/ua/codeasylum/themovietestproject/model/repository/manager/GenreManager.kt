package ua.codeasylum.themovietestproject.model.repository.manager


import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import ua.codeasylum.themovietestproject.model.networkDto.Genre

import ua.codeasylum.themovietestproject.model.networkDto.GenreDto
import ua.codeasylum.themovietestproject.model.repository.genre.GenreApiRepository
import ua.codeasylum.themovietestproject.model.repository.genre.GenreCacheRepository

class GenreManager constructor(
    private val genreApiRepository: GenreApiRepository,
    private val genreCacheRepository: GenreCacheRepository
) : GenreManagerInterface {

    override fun fetchGenres(): Single<GenreDto> =
        genreCacheRepository.getGenres()
            .flatMap { it ->
                if (it.genres.isEmpty())
                    genreApiRepository.getGenres()
                        .map {
                            genreCacheRepository.save(it)
                            return@map it
                        }
                else Single.just(it)
            }

    override fun getGenresStringById(list: MutableList<Int>): Single<String> =
        fetchGenres()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .flatMap {
                var genreString = ""
                for (genre in it.genres)
                    genreString += appendGenreData(genreString, genre)
                return@flatMap Single.just(genreString)

            }


    private fun appendGenreData(genresString: String, genre: Genre): String {
        var genresText = genresString

        genresText += if (genresText.isNotEmpty())
            ",${genre.name}"
        else
            genre.name

        return genresText

    }
}
