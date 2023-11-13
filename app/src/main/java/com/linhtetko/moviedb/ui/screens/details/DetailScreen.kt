package com.linhtetko.moviedb.ui.screens.details

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Snackbar
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.linhtetko.moviedb.R
import com.linhtetko.moviedb.ui.theme.MovieDBTheme

@Composable
fun DetailScreen(modifier: Modifier = Modifier, id: Int, viewModel: DetailViewModel) {
    val scrollState = rememberScrollState()

    LaunchedEffect(key1 = id) {
        viewModel.getMovie(id)
    }

    Box(
        modifier = modifier
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = modifier
                .fillMaxSize()
                .verticalScroll(scrollState)
        ) {
            AsyncImage(
                model = viewModel.movie?.getImage(),
                contentDescription = viewModel.movie?.title
            )
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        horizontal = dimensionResource(
                            id = R.dimen.size_space_2x
                        )
                    )
                    .padding(
                        top = dimensionResource(
                            id = R.dimen.size_space_2x
                        )
                    ),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = viewModel.movie?.title ?: "",
                    fontSize = MaterialTheme.typography.titleLarge.fontSize
                )
                IconButton(onClick = viewModel::updateMovie) {
                    Icon(
                        imageVector = if (viewModel.movie?.isFavourite == true) Icons.Default.Favorite else Icons.Outlined.FavoriteBorder,
                        contentDescription = null
                    )
                }
            }
            Text(
                text = viewModel.movie?.overview ?: "", modifier = Modifier.padding(
                    dimensionResource(
                        id = R.dimen.size_space_2x
                    )
                )
            )

            if (!viewModel.message.isNullOrEmpty() || !viewModel.error.isNullOrEmpty()) {
                Snackbar {
                    Text(text = viewModel.message ?: viewModel.error ?: "Something Wrong")
                }
            }
        }

        if (viewModel.loading) {
            CircularProgressIndicator()
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun DetailScreenPreview() {
    MovieDBTheme {
        DetailScreen(
            id = 0,
            viewModel = hiltViewModel()
        )
    }
}