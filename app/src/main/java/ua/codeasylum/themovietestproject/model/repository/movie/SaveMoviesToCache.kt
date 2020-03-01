package ua.codeasylum.themovietestproject.model.repository.movie

import ua.codeasylum.themovietestproject.model.networkDto.MovieDto

interface SaveMoviesToCache {

    fun saveUpcoming(movieDto: MovieDto)

    fun saveTopRated(movieDto: MovieDto)
}