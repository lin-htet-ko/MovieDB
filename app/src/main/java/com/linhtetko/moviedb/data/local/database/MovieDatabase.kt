package com.linhtetko.moviedb.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.linhtetko.moviedb.data.local.database.daos.MovieDao
import com.linhtetko.moviedb.data.local.database.utils.DbSchemas
import com.linhtetko.moviedb.data.remote.vos.MovieVO

@Database(entities = [MovieVO::class], exportSchema = false, version = DbSchemas.DB_VERSION)
abstract class MovieDatabase : RoomDatabase() {

    abstract fun movieDao(): MovieDao
}