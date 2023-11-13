package com.linhtetko.moviedb.domain.utils

import com.linhtetko.moviedb.data.remote.vos.MovieVO
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import javax.inject.Inject

object MoshiJsonParser: JsonParser {

    private val moshi: Moshi = Moshi.Builder().build()

    override fun toMovie(json: String): MovieVO? {
        return moshi.adapter(MovieVO::class.java).fromJson(json)
    }

    override fun toIntList(json: String): List<Int>? {
        return moshi.adapter<List<Int>>(List::class.java).fromJson(json)
    }

    override fun toJson(data: MovieVO): String? {
        return moshi.adapter(MovieVO::class.java).toJson(data)
    }

    override fun toJson(data: List<Int>): String? {
        return moshi.adapter<List<Int>>(List::class.java).toJson(data)
    }
}