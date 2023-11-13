package com.linhtetko.moviedb.domain.respositories

import com.linhtetko.moviedb.data.local.database.daos.MovieDao
import com.linhtetko.moviedb.data.local.database.utils.DbSchemas
import com.linhtetko.moviedb.data.remote.api.MovieDbApi
import com.linhtetko.moviedb.data.remote.vos.MovieVO
import com.linhtetko.moviedb.domain.respositories.base.MovieRepository
import com.linhtetko.moviedb.domain.utils.extensions.switchThread
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.disposables.CompositeDisposable
import javax.inject.Inject

class MovieRepoImpl @Inject constructor(
    private val api: MovieDbApi,
    private val movieDao: MovieDao
) : MovieRepository {

    override fun getUpComingMovies(
        cmp: CompositeDisposable,
        onFailure: (String?) -> Unit
    ): Observable<List<MovieVO>> {
        val onlineCmp = api.getUpComingMovies().map {
            it.results?.map { item -> item.copy(type = DbSchemas.TYPE_MOVIE_UPCOMING) }
                ?: listOf()
        }.flatMap {
            val favs = it.filter { item ->
                movieDao.searchForFavMovieOneTime(item.id) != null
            }
            val temp = it.toMutableList()
            temp.removeAll(favs)
            movieDao.insert(*temp.toTypedArray()).toObservable()
        }.switchThread().subscribe({}, {
            onFailure(it.localizedMessage)
        })
        cmp.add(onlineCmp)

        return movieDao.queryMovies(DbSchemas.TYPE_MOVIE_UPCOMING).toObservable()
    }

    override fun getPopularMovies(
        cmp: CompositeDisposable,
        onFailure: (String?) -> Unit
    ): Observable<List<MovieVO>> {
        val onlineCmp = api.getPopularMovies()
            .map {
                it.results?.map { item -> item.copy(type = DbSchemas.TYPE_MOVIE_POPULAR) }
                    ?: listOf()
            }.flatMap {
                val favs = it.filter { item ->
                    movieDao.searchForFavMovieOneTime(item.id) != null
                }
                val temp = it.toMutableList()
                temp.removeAll(favs)
                movieDao.insert(*temp.toTypedArray()).toObservable()
            }.switchThread().subscribe({}, {
                onFailure(it.localizedMessage)
            })
        cmp.add(onlineCmp)

        return movieDao.queryMovies(DbSchemas.TYPE_MOVIE_POPULAR).toObservable()
    }

    override fun updateMovie(movie: MovieVO): Observable<Int> {
        return movieDao.update(movie).toObservable().switchThread()
    }

    override fun getMovie(id: Int): Observable<MovieVO> {
        return movieDao.searchMovie(id).switchThread()
    }
}