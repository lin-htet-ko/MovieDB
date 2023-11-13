package com.linhtetko.moviedb.data.remote.api

import com.linhtetko.moviedb.data.remote.response.BaseResponse
import com.linhtetko.moviedb.data.remote.vos.MovieVO
import io.reactivex.rxjava3.core.Observable
import retrofit2.http.GET

interface MovieDbApi {

    @GET(ApiSchemas.Routes.GET_UPCOMING)
    fun getUpComingMovies(): Observable<BaseResponse<List<MovieVO>>>

    @GET(ApiSchemas.Routes.GET_POPULAR)
    fun getPopularMovies(): Observable<BaseResponse<List<MovieVO>>>

}