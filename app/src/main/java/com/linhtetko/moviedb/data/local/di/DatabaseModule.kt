package com.linhtetko.moviedb.data.local.di

import android.content.Context
import androidx.room.Room
import com.linhtetko.moviedb.data.local.database.MovieDatabase
import com.linhtetko.moviedb.data.local.database.daos.MovieDao
import com.linhtetko.moviedb.data.local.database.utils.DbSchemas
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {

    @Provides
    @Singleton
    fun getMoviesDb(@ApplicationContext context: Context): MovieDatabase =
        Room.databaseBuilder(context, MovieDatabase::class.java, DbSchemas.DB_NAME)
            .build()

    @Provides
    @Singleton
    fun getMoviesDao(database: MovieDatabase): MovieDao = database.movieDao()
}