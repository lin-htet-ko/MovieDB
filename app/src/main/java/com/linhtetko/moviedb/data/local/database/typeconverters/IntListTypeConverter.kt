package com.linhtetko.moviedb.data.local.database.typeconverters

import androidx.room.TypeConverter
import com.linhtetko.moviedb.domain.utils.JsonParser
import com.linhtetko.moviedb.domain.utils.MoshiJsonParser


class IntListTypeConverter {

    val jsonParser: JsonParser = MoshiJsonParser

    @TypeConverter
    fun toList(json: String): List<Int>? = jsonParser.toIntList(json)

    @TypeConverter
    fun fromList(list: List<Int>): String? = jsonParser.toJson(list)
}