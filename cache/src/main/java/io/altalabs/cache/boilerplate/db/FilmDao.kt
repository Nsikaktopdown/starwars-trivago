package io.altalabs.cache.boilerplate.db

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query
import io.altalabs.cache.boilerplate.entity.CacheFilmEntity
import io.reactivex.Single

@Dao
interface FilmDao {

    @Query("SELECT * FROM starWarsFilms")
    fun getFilms(): Single<List<CacheFilmEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveFilms(list: List<CacheFilmEntity>)

    @Query("DELETE FROM starWarsFilms")
    fun clearFilms()

    @Query("SELECT * FROM starWarsFilms WHERE episode_id = :id")
    fun getFilmWithId(id: Int): Single<CacheFilmEntity>
}