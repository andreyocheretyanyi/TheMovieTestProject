package ua.codeasylum.themovietestproject.viewmodel

import androidx.lifecycle.MutableLiveData
import ua.codeasylum.themovietestproject.App
import ua.codeasylum.themovietestproject.model.repository.manager.GenreManagerInterface
import ua.codeasylum.themovietestproject.view.MovieDetailFragmentArgs
import javax.inject.Inject

class MovieDetailViewModel @Inject constructor(
    app: App, private val genresManager: GenreManagerInterface
) : BaseViewModel(app) {
    val movieTitle = MutableLiveData("")
    val movieImage = MutableLiveData("")
    val movieDescription = MutableLiveData("")
    val originalLang = MutableLiveData("")
    val rDate = MutableLiveData("")
    val rating = MutableLiveData("")
    val genres = MutableLiveData("")

    fun performArgs(movieDetailFragmentArgs: MovieDetailFragmentArgs) {
        with(movieDetailFragmentArgs.movie) {
            movieTitle.value = title
            movieImage.value = posterPath ?: ""
            movieDescription.value = overview
            originalLang.value = originalLanguage
            rDate.value = releaseDate
            rating.value = voteAverage.toString()

            genresManager.getGenresStringById(genreIds)
                .subscribe({
                    genres.value = it
                }, {
                    error.value = Error(it)
                })
        }
    }
}