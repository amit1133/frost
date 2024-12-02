package com.amit.frost.home.presentation.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.amit.frost.R
import com.amit.frost.home.presentation.model.WeatherInfoUI
import com.amit.frost.home.presentation.model.previewWeatherInfo
import com.amit.frost.ui.theme.FrostTheme


@Composable
fun WeatherInfoMainCard(
    modifier: Modifier = Modifier,
    weatherUI: WeatherInfoUI
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .heightIn(min = 80.dp, max = 300.dp),
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 40.dp, vertical = 28.dp)
        ) {
            Text(
                weatherUI.city,
                modifier = Modifier
                    .align(Alignment.CenterHorizontally),
                style = MaterialTheme.typography.labelLarge

            )
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                WeatherInfoPrimaryDataItem(weatherUI = weatherUI)
                AsyncImage(
                    model = weatherUI.iconUrl.bigImageURL,
                    contentDescription = null,
                    modifier = Modifier
                        .size(110.dp)
                        .shadow(
                            elevation = 60.dp,
                            shape = CircleShape,
                            clip = false
                        ),
                    placeholder = painterResource(R.drawable.sky),
                    error = painterResource(R.drawable.sky)
                )

            }

        }

    }

}

@PreviewLightDark
@Composable
private fun WeatherInfoMainCardPreview() {
    FrostTheme {
        WeatherInfoMainCard(weatherUI = previewWeatherInfo)
    }
}

