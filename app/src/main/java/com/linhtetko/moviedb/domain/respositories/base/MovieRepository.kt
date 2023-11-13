package com.linhtetko.moviedb.domain.respositories.base

import com.linhtetko.moviedb.data.remote.vos.MovieVO
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.disposables.CompositeDisposable

interface MovieRepository {

    fun getUpComingMovies(
        cmp: CompositeDisposable,
        onFailure: (String?) -> Unit
    ): Observable<List<MovieVO>>

    fun getPopularMovies(
        cmp: CompositeDisposable,
        onFailure: (String?) -> Unit
    ): Observable<List<MovieVO>>

    fun updateMovie(movie: MovieVO): Observable<Int>

    fun getMovie(id: Int): Observable<MovieVO>
}