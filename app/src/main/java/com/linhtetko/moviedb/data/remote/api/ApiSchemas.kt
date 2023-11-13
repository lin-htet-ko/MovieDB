package com.linhtetko.moviedb.data.remote.api

object ApiSchemas {

    const val API_KEY =
        "eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiIyZmM0NTIzNzdmYTNiN2FiYmU5YTViN2ExM2Y3MjQ5MSIsInN1YiI6IjYyY2VjOTRlZDRjYzhlMDA0ZTI4MDVhNCIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.PzAjQ45nm4dyuJdsRMAyA9yJdtwZ54i7P4G8xJmYV2g"

    const val HEADER_AUTH = "Authorization"
    const val IMAGE_BASE_URL = "https://image.tmdb.org/t/p/original/"

    object Routes {
        const val BASE_URL = "https://api.themoviedb.org/3/movie/"
        const val GET_UPCOMING = "upcoming"
        const val GET_POPULAR = "popular"
    }

}