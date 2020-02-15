package ua.codeasylum.themovietestproject.model.repository

interface SaveToCache<T> {
    fun save(data: T)
}