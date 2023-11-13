package com.linhtetko.moviedb.domain.utils

import com.linhtetko.moviedb.data.remote.vos.MovieVO

interface JsonParser {

    fun toMovie(json: String): MovieVO?

    fun toIntList(json: String): List<Int>?

    fun toJson(data: MovieVO): String?

    fun toJson(data: List<Int>): String?

}