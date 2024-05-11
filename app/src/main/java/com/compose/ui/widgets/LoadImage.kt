package com.compose.ui.widgets

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import coil.compose.AsyncImage
import coil.request.ImageRequest
import coil.transform.CircleCropTransformation
import com.compose.R

@Composable
fun LoadImage(
    modifier: Modifier = Modifier,
    data: String?,
    placeholder: Int,
    error: Int,
) {
    val context = LocalContext.current
    AsyncImage(
        modifier = modifier,
        model = ImageRequest.Builder(context)
            .data(data)
            .crossfade(true)
            .placeholder(placeholder)
            .error(error)
            .build(),
        contentScale = ContentScale.Crop,
        contentDescription = stringResource(R.string.load_image),
    )
}

@Composable
fun LoadCircularImage(
    modifier: Modifier = Modifier,
    data: String?,
    placeholder: Int,
    error: Int,
) {
    val context = LocalContext.current
    AsyncImage(
        modifier = modifier,
        model = ImageRequest.Builder(context)
            .data(data)
            .crossfade(true)
            .placeholder(placeholder)
            .error(error)
            .transformations(CircleCropTransformation())
            .build(),
        contentScale = ContentScale.Crop,
        contentDescription = stringResource(R.string.load_image),
    )
}

@Composable
fun LoadImageBig(
    modifier: Modifier = Modifier,
    image: Int,
) {
    Image(
        modifier = modifier
            .size(
                width = dimensionResource(id = R.dimen.image_big),
                height = dimensionResource(id = R.dimen.image_big),
            ),
        painter = painterResource(image),
        contentDescription = stringResource(R.string.illustration_logo),
    )
}
