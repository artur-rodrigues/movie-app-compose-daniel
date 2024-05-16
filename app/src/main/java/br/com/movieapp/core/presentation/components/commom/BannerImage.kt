package br.com.movieapp.core.presentation.components.commom

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import br.com.movieapp.R
import coil.compose.AsyncImage
import coil.request.ImageRequest

@Composable
fun BannerImage(
    url: String,
    contentDescription: String? = null,
    contentScale: ContentScale = ContentScale.Crop
) {
    AsyncImage(
        modifier = Modifier.fillMaxWidth(),
        model = ImageRequest
            .Builder(LocalContext.current)
            .data(url)
            .crossfade(true)
            .error(R.drawable.ic_error_image)
            .placeholder(R.drawable.ic_placeholder)
            .build(),
        contentDescription = contentDescription,
        contentScale = contentScale
    )
}