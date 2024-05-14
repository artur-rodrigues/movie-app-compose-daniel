package br.com.movieapp.search_movie_feature.presentation.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.com.movieapp.R
import br.com.movieapp.search_movie_feature.presentation.MoviesSearchEvent
import br.com.movieapp.ui.theme.white

@Composable
fun SearchComponent(
    modifier: Modifier = Modifier,
    query: String,
    onSearch: (String) -> Unit,
    onQuerySearchEvent: (MoviesSearchEvent) -> Unit
) {
    val unfocusedColor = white.copy(alpha = 0.3f)
    val keyBoard = LocalSoftwareKeyboardController.current

    OutlinedTextField(
        value = query,
        onValueChange = {
            onQuerySearchEvent(MoviesSearchEvent.EnteredQuery(it))
        },
        trailingIcon = {
            IconButton(onClick = {
                keyBoard?.hide()
                onSearch(query)
            }) {
                Icon(imageVector = Icons.Outlined.Search, contentDescription = null)
            }
        },
        placeholder = {
            Text(text = stringResource(id = R.string.search_movies))
        },
        keyboardOptions = KeyboardOptions.Default.copy(
            capitalization = KeyboardCapitalization.Sentences,
            autoCorrect = true,
            keyboardType = KeyboardType.Text,
            imeAction = ImeAction.Search
        ),
        keyboardActions = KeyboardActions(
            onSearch = {
                keyBoard?.hide()
                onSearch(query)
            }
        ),
        colors = OutlinedTextFieldDefaults.colors(
            cursorColor = white,
            focusedTextColor = white,
            focusedPlaceholderColor = white,
            focusedTrailingIconColor = white,
            focusedLabelColor = white,
            unfocusedTextColor = unfocusedColor,
            unfocusedPlaceholderColor = unfocusedColor,
            unfocusedTrailingIconColor = unfocusedColor,
            unfocusedLabelColor = unfocusedColor,
        ),
        modifier = modifier.fillMaxWidth().heightIn(min = 60.dp)
    )
}

@Preview
@Composable
fun SearchComponentPreview() {
    SearchComponent(query = "War", onSearch = {}) {}
}