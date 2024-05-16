package br.com.movieapp.movie_detail_feature.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.com.movieapp.R
import coil.compose.AsyncImage
import coil.request.ImageRequest

@Composable
fun MovieDetailBackdropImage(
    imageUrl: String?,
    modifier: Modifier = Modifier,
) {
    Box(modifier = modifier) {
        imageUrl?.let {
            AsyncImage(
                modifier = Modifier.fillMaxWidth(),
                model = ImageRequest
                    .Builder(LocalContext.current)
                    .data(imageUrl)
                    .crossfade(true)
                    .error(R.drawable.ic_error_image)
                    .placeholder(R.drawable.ic_placeholder)
                    .build(),
                contentDescription = null,
                contentScale = ContentScale.Crop
            )
        } ?: run {
            Image(
                modifier = Modifier.fillMaxSize(),
                painter = painterResource(id = R.drawable.ic_placeholder),
                contentDescription = null,
                contentScale = ContentScale.Crop
            )
        }
    }
}

@Preview
@Composable
private fun MovieDetailBackdropImagePrev() {
    MovieDetailBackdropImage(
        "https://recreio.uol.com.br/media/_versions/filmes/avatar_capa_widelg.jpg",
        Modifier
            .fillMaxWidth()
            .height(200.dp)
    )
}