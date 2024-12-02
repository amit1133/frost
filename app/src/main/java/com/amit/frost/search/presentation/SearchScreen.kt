package com.amit.frost.search.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.amit.frost.R
import com.amit.frost.core.presentation.component.FrostTopNavAppBar
import com.amit.frost.search.presentation.component.SuggestionsList
import com.amit.frost.search.presentation.model.CityData


@Composable
fun SearchScreen(
    state: SearchState,
    onItemClick: (CityData) -> Unit,
    onSearchQueryChange: (String) -> Unit,
    onNavBackClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Scaffold(modifier = modifier, topBar = {
        FrostTopNavAppBar(
            title = stringResource(R.string.search), onNavBackClick = onNavBackClick
        )
    }) { padding ->
        if (state.isLoading) {
            Box(
                modifier = modifier.fillMaxSize(), contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        } else {
            Box(
                modifier = modifier
                    .fillMaxSize()
                    .padding(padding)
            ) {
                state.searchUI?.let {
                    Column(
                        modifier = Modifier
                            .padding(horizontal = 32.dp)
                            .fillMaxWidth(),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        OutlinedTextField(
                            value = state.query,
                            onValueChange = onSearchQueryChange,
                            label = { Text(stringResource(R.string.type_city_name)) },
                            modifier = Modifier.fillMaxWidth()
                        )
                        when {
                            state.cities.isEmpty() && state.query.isNotBlank() -> {
                                Box(
                                    modifier = modifier.fillMaxSize(),
                                    contentAlignment = Alignment.Center
                                ) {
                                    Text(stringResource(R.string.no_data_found))
                                }
                            }

                            state.cities.isNotEmpty() -> {
                                SuggestionsList(state = state, clickedCity = {
                                    onItemClick(it)
                                })
                            }

                            else -> {
                                Box(
                                    modifier = modifier.fillMaxSize(),
                                    contentAlignment = Alignment.Center
                                ) {
                                    Text(stringResource(R.string.search_suggestion))
                                }
                            }
                        }
                    }
                }
            }
        }
    }

}