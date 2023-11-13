package com.linhtetko.moviedb.data.local.database.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.linhtetko.moviedb.data.local.database.utils.DbSchemas
import com.linhtetko.moviedb.data.remote.vos.MovieVO
import io.reactivex.rxjava3.core.Flowable
import io.reactivex.rxjava3.core.Maybe
import io.reactivex.rxjava3.core.Observable

@Dao
interface MovieDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(vararg movies: MovieVO): Maybe<List<Long>>

    @Query("SELECT * FROM ${DbSchemas.TB_MOVIE} WHERE id = :id AND isFavourite = 1")
    fun searchForFavMovieOneTime(id: Int): MovieVO?

    @Query("SELECT * FROM ${DbSchemas.TB_MOVIE} WHERE id = :id")
    fun searchMovie(id: Int): Observable<MovieVO>

    @Query("SELECT * FROM ${DbSchemas.TB_MOVIE} WHERE type=:type")
    fun queryMovies(type: String): Flowable<List<MovieVO>>

    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun update(movie: MovieVO): Maybe<Int>
}