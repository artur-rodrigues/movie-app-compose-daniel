package br.com.movieapp.movie_detail_feature.presentation.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.com.movieapp.R
import br.com.movieapp.ui.theme.white

@Composable
fun MovieDetailOverview(
    overview: String,
    modifier: Modifier = Modifier
) {
    var expanded by remember { mutableStateOf(false) }

    Column(
        modifier = modifier
            .clickable {
            expanded = !expanded
        },
        verticalArrangement = Arrangement.SpaceEvenly,
        horizontalAlignment = Alignment.Start
    ) {
        Text(
            text = if (overview.isNotEmpty()) stringResource(id = R.string.description) else "",
            color = white,
            fontFamily = FontFamily.SansSerif,
            fontWeight = FontWeight.ExtraBold,
            fontSize = 20.sp
        )

        Text(
            text = overview,
            color = Color.DarkGray,
            fontFamily = FontFamily.SansSerif,
            maxLines = if(expanded) Int.MAX_VALUE else 3,
            overflow = TextOverflow.Ellipsis,
            fontSize = 15.sp
        )
    }
}

@Preview
@Composable
private fun MovieDetailOverviewPreview() {
    MovieDetailOverview(
        overview = "Filme Filme\nFilme Filme\nFilme Filme\nFilme Filme\nFilme Filme",
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp)
    )
}