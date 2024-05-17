package br.com.movieapp.core.presentation.components.commom

import androidx.annotation.StringRes
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import br.com.movieapp.ui.theme.black
import br.com.movieapp.ui.theme.white

@ExperimentalMaterial3Api
@Composable
fun MovieAppBar(
    @StringRes stringId: Int
) {
    TopAppBar(
        title = {
            Text(text = stringResource(id = stringId), color = white)
        },
        colors = TopAppBarDefaults.topAppBarColors(containerColor = black)
    )
}