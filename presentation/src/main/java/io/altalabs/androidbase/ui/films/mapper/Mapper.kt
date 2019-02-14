package io.altalabs.androidbase.ui.films.mapper

/**
 * Interface for model mappers. It provides helper methods that facilitate
 * retrieving of models from outer data source layers
 *
 * @param <T> the domain model input type
 * @param <V> the model return type
 */
interface Mapper<T, V> {

    fun mapFromDomain(type: T): V


}