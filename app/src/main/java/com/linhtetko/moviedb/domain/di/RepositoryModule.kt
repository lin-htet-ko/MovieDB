package com.linhtetko.moviedb.domain.di

import com.linhtetko.moviedb.domain.respositories.MovieRepoImpl
import com.linhtetko.moviedb.domain.respositories.base.MovieRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface RepositoryModule {

    @Binds
    @Singleton
    fun bindUpComingRepo(impl: MovieRepoImpl): MovieRepository
}