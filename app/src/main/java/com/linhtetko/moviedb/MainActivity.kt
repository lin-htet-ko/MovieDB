package com.linhtetko.moviedb

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.linhtetko.moviedb.ui.screens.Screen
import com.linhtetko.moviedb.ui.screens.details.DetailScreen
import com.linhtetko.moviedb.ui.screens.details.DetailViewModel
import com.linhtetko.moviedb.ui.screens.home.HomeScreen
import com.linhtetko.moviedb.ui.screens.home.HomeViewModel
import com.linhtetko.moviedb.ui.theme.MovieDBTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            MovieDBTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MovieApp()
                }
            }
        }
    }
}

@Composable
fun MovieApp() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Screen.Home.route) {
        composable(Screen.Home.route) {
            val viewModel = hiltViewModel<HomeViewModel>()
            HomeScreen(viewModel = viewModel, navController = navController)
        }

        composable(Screen.Detail.route, arguments = listOf(
            navArgument("id"){
                type = NavType.IntType
            }
        )) {
            val viewModel = hiltViewModel<DetailViewModel>()
            val id = rememberSaveable {
                mutableStateOf(it.arguments?.getInt("id"))
            }
            if (id.value != null) {
                DetailScreen(id = id.value!!, viewModel = viewModel)
            }
        }
    }
}