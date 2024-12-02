package com.amit.frost.home.presentation

import com.amit.frost.core.domain.LatLon
import com.amit.frost.home.presentation.model.WeatherInfoUI

data class WeatherInfoState(
    val isLoading: Boolean = false,
    val info: WeatherInfoUI? = null,
    val latLon: LatLon? = null,
    val hasLocationPermission: Boolean = false
)
