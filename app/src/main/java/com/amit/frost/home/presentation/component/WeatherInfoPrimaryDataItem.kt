package com.amit.frost.home.presentation.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.PreviewLightDark
import com.amit.frost.home.presentation.model.WeatherInfoUI
import com.amit.frost.home.presentation.model.previewWeatherInfo
import com.amit.frost.ui.theme.FrostTheme


@Composable
fun WeatherInfoPrimaryDataItem(
    weatherUI: WeatherInfoUI
) {
    Column {
        Text(
            weatherUI.currentTemperature.formatted,
            style = MaterialTheme.typography.displayLarge
        )

        Text(
            "Feels like ${weatherUI.feelsLike.formatted}",
            style = MaterialTheme.typography.bodySmall
        )
        Row {
            Text(
                "Max ${weatherUI.maxTemperature.formatted} - Min ${weatherUI.minTemperature.formatted}",
                style = MaterialTheme.typography.bodySmall
            )
        }
        Text(
            weatherUI.weatherStatus,
            style = MaterialTheme.typography.bodySmall
        )
    }


}


@PreviewLightDark
@Composable
private fun WeatherInfoPrimaryDataItem() {
    FrostTheme {
        WeatherInfoPrimaryDataItem(weatherUI = previewWeatherInfo)
    }
}
