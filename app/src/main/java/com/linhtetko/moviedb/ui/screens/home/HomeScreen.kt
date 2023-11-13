package com.linhtetko.moviedb.ui.screens.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Snackbar
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.linhtetko.moviedb.R
import com.linhtetko.moviedb.data.remote.vos.MovieVO
import com.linhtetko.moviedb.ui.components.MovieUiItem
import com.linhtetko.moviedb.ui.screens.Screen
import com.linhtetko.moviedb.ui.theme.MovieDBTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    navController: NavController,
    viewModel: HomeViewModel
) {

    Scaffold(modifier = modifier.fillMaxSize()) {
        Box(
            modifier = Modifier
                .padding(it)
                .fillMaxSize(), contentAlignment = Alignment.Center
        ) {
            Column(
                modifier = Modifier
                    .padding(it)
                    .fillMaxSize()
            ) {

                MovieSection(
                    label = R.string.lbl_popular_movies,
                    movies = viewModel.popularMoviesState,
                    onTapFav = viewModel::onTapFav,
                    onTapItem = { item -> navController.navigate(Screen.Detail.route(item.id)) })
                MovieSection(
                    label = R.string.lbl_upcoming_movies,
                    movies = viewModel.upComingMovieState,
                    onTapFav = viewModel::onTapFav,
                    onTapItem = { item -> navController.navigate(Screen.Detail.route(item.id)) })

                if (!viewModel.message.isNullOrEmpty() || !viewModel.error.isNullOrEmpty()) {
                    Snackbar(
                        modifier = Modifier.padding(dimensionResource(id = R.dimen.size_space_general)),
                        action = {
                            if (!viewModel.error.isNullOrEmpty())
                                IconButton(onClick = {
                                    viewModel.getPopularMovies()
                                    viewModel.getUpComingMovies()
                                }) {
                                    Icon(
                                        imageVector = Icons.Default.Refresh,
                                        contentDescription = null
                                    )
                                }
                        }) {
                        Text(
                            text = viewModel.message ?: viewModel.error ?: "Something Wrong"
                        )
                    }
                }
            }

            if (viewModel.loading) {
                CircularProgressIndicator()
            }
        }
    }
}

@Composable
fun ColumnScope.MovieSection(
    label: Int,
    movies: List<MovieVO> = listOf(),
    onTapFav: (MovieVO) -> Unit,
    onTapItem: (MovieVO) -> Unit,
) {
    if (movies.isNotEmpty()) {
        Text(
            modifier = Modifier.padding(
                top = dimensionResource(id = R.dimen.size_space_2x),
                start = dimensionResource(id = R.dimen.size_space_2x)
            ),
            text = stringResource(id = label),
            fontWeight = MaterialTheme.typography.headlineMedium.fontWeight,
            fontSize = MaterialTheme.typography.headlineSmall.fontSize
        )
        Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.size_space_2x)))
        LazyHorizontalGrid(
            modifier = Modifier.weight(1f),
            rows = GridCells.Fixed(2),
            verticalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.size_space_2x)),
            horizontalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.size_space_2x))
        ) {
            items(movies) { item ->
                MovieUiItem(
                    title = item.title ?: "",
                    imageUrl = item.backdropPath,
                    isFavourite = item.isFavourite,
                    onTapFav = { onTapFav(item) },
                    onTapItem = { onTapItem(item) }
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun HomeScreenPreview() {
    MovieDBTheme {
        HomeScreen(navController = rememberNavController(), viewModel = hiltViewModel())
    }
}