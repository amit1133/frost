package com.amit.frost.home.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.PreviewLightDark
import com.amit.frost.R
import com.amit.frost.home.presentation.component.WeatherInfoMainCard
import com.amit.frost.home.presentation.model.previewWeatherInfo
import com.amit.frost.ui.theme.FrostTheme
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberPermissionState


@OptIn(ExperimentalMaterial3Api::class, ExperimentalPermissionsApi::class)
@Composable
fun WeatherInfoScreen(
    modifier: Modifier = Modifier,
    onAction: (WeatherInfoAction) -> Unit,
    state: WeatherInfoState,
    onRequestPermission: (Boolean) -> Unit,
    onFetchLocation: () -> Unit
) {

    val locationPermissionState =
        rememberPermissionState(android.Manifest.permission.ACCESS_FINE_LOCATION)
    LaunchedEffect(locationPermissionState) {
        if (locationPermissionState.status.isGranted) {
            onFetchLocation()
        } else {
            onRequestPermission(false)
        }
    }

    Scaffold(modifier = modifier, topBar = {
        TopAppBar(actions = {
            IconButton(onClick = { onAction.invoke(WeatherInfoAction.OnSearchClick) }) {
                Icon(Icons.Default.Search, contentDescription = null)
            }
        }, title = { Text(stringResource(R.string.weather_now)) })
    }) { padding ->

        when {
            state.isLoading -> {
                Box(
                    modifier = modifier.fillMaxSize(), contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator()
                }
            }

            !state.hasLocationPermission && state.latLon?.lat == 0.0 && state.latLon?.lon == 0.0 -> {
                Box(
                    modifier = modifier.fillMaxSize(), contentAlignment = Alignment.Center
                ) {
                    Column(
                        modifier = modifier.fillMaxSize(),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        Text(text = stringResource(R.string.permission_message))
                        Button(onClick = {
                            locationPermissionState.launchPermissionRequest()
                        }) {
                            Text(text = stringResource(R.string.grant_permission))
                        }
                    }
                }
            }

            else ->
                state.info?.let {
                    WeatherInfoMainCard(weatherUI = it, modifier = modifier.padding(padding))
                }
        }
    }
}


@PreviewLightDark
@Composable
private fun WeatherInfoMainCardPreview() {
    FrostTheme {
        WeatherInfoScreen(
            state = WeatherInfoState().copy(info = previewWeatherInfo), onAction = {},
            onRequestPermission = {},
            onFetchLocation = {},
        )
    }
}

