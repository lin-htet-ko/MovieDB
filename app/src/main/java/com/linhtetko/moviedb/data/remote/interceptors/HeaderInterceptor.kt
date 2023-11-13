package com.linhtetko.moviedb.data.remote.interceptors

import com.linhtetko.moviedb.data.remote.api.ApiSchemas
import okhttp3.Interceptor
import okhttp3.Response

class HeaderInterceptor: Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request().newBuilder()
            .addHeader(ApiSchemas.HEADER_AUTH, "Bearer ${ApiSchemas.API_KEY}").build()
        return chain.proceed(request)
    }
}