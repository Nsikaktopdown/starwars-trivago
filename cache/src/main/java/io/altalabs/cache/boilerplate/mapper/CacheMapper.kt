package io.altalabs.cache.boilerplate.mapper

/**
 * Interface for model mappers. It provides helper methods that facilitate
 * retrieving of models from outer data source layers
 *
 * @param <T> the cached model input type
 * @param <V> the model return type
 */
interface CacheMapper<T, V> {

    fun mapFromData(type: T): V

    fun mapFromCache(type: V): T


}