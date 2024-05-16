package br.com.movieapp.movie_detail_feature.presentation.components

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.com.movieapp.R
import br.com.movieapp.core.domain.model.MovieDetails

@Composable
fun MovieInfoContent(
    movieDetails: MovieDetails?,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        movieDetails?.let {
            MovieInfo(
                stringId = R.string.vote_average,
                value = it.voteAverage.toString()
            )
            MovieInfo(
                stringId = R.string.duration,
                value = stringResource(R.string.duration_minutes, it.duration.toString())
            )
            MovieInfo(
                stringId = R.string.release_date,
                value = it.releaseDate
            )
        }
    }
}

@Composable
fun MovieInfo(
    @StringRes stringId: Int,
    value: String,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
    ) {
        MovieDetailText(
            text = stringResource(id = stringId),
            style = MaterialTheme.typography.titleSmall.copy(fontSize = 13.sp, letterSpacing = 1.sp)
        )

        MovieDetailText(
            text = value,
            style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.SemiBold),
            modifier = Modifier.padding(top = 4.dp)
        )
    }
}

@Composable
fun ColumnScope.MovieDetailText(
    text: String,
    style: TextStyle,
    modifier: Modifier = Modifier,
) {
    Text(
        text = text,
        style = style,
        color = Color.DarkGray,
        modifier = modifier.align(Alignment.CenterHorizontally)
    )
}

@Preview
@Composable
private fun MovieInfoContentPreview() {
    MovieInfoContent(
        MovieDetails(
            id = 0,
            title = "Avatar",
            genres = emptyList(),
            overview = "Não Tem",
            backdropPath = "",
            releaseDate = "Foi Hoje",
            voteAverage = 5.0,
            duration = 180,
            voteCount = 1000,
        )
    )
}