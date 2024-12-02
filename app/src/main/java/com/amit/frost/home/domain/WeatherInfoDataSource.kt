package com.amit.frost.home.domain

import com.amit.frost.core.domain.util.NetworkError
import com.amit.frost.core.domain.util.Result

interface WeatherInfoDataSource {
    suspend fun getWeatherByLatLon(
        lat: Double,
        lon: Double
    ): Result<WeatherInfo, NetworkError>
}