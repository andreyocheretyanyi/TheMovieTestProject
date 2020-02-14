package ua.codeasylum.themovietestproject.model.repository

interface CanSaveToCache<T> {
    fun save(data: T)

}