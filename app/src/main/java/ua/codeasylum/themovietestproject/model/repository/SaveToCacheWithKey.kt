package ua.codeasylum.themovietestproject.model.repository

interface SaveToCacheWithKey<K, V> {
    fun save(key: K, value: V)
}