package com.linhtetko.moviedb.ui.screens

sealed class Screen(val route: String) {

    companion object Route {
        const val HOME = "/home"
        const val DETAIL = "/detail"
    }

    data object Home : Screen(HOME)

    data object Detail : Screen("$DETAIL/{id}") {

        fun route(id: Int) = "$DETAIL/$id"

    }

}
