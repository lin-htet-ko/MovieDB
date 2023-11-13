package com.linhtetko.moviedb.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.linhtetko.moviedb.R
import com.linhtetko.moviedb.data.remote.api.ApiSchemas.IMAGE_BASE_URL

@Composable
fun MovieUiItem(
    modifier: Modifier = Modifier,
    title: String,
    imageUrl: String?,
    isFavourite: Boolean,
    onTapFav: () -> Unit,
    onTapItem: () -> Unit
) {
    Box(modifier = modifier
        .width(200.dp)
        .clickable { onTapItem() }) {
        Column {
            AsyncImage(
                model = IMAGE_BASE_URL + imageUrl,
                contentDescription = title,
            )
            Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.size_space_general)))
            Text(text = title)
        }
        IconButton(onClick = onTapFav, modifier = Modifier.align(Alignment.TopEnd)) {
            Icon(
                imageVector = if (isFavourite) Icons.Filled.Favorite else Icons.Outlined.FavoriteBorder,
                contentDescription = null
            )
        }
    }
}