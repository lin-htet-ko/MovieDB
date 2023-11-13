package com.linhtetko.moviedb.data.remote.response

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class BaseResponse<T>(val results: T? = null)
