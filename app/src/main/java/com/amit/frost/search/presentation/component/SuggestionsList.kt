package com.amit.frost.search.presentation.component

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ListItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.amit.frost.search.presentation.SearchState
import com.amit.frost.search.presentation.model.CityData


@Composable
fun SuggestionsList(
    modifier: Modifier = Modifier,
    state: SearchState,
    clickedCity: (CityData) -> Unit
) {
    LazyColumn {
        items(state.cities) { data ->
            ListItem(
                headlineContent = { Text("${data.city}") },
                modifier = modifier.clickable {
                    clickedCity(data)
                },
            )

        }
    }
}

@Preview
@Composable
private fun SuggestionsListPreview() {
    SuggestionsList(
        state = SearchState().copy(cities = listOf(CityData("Dhaka", 0.0, 0.0))),
        clickedCity = {}
    )


}