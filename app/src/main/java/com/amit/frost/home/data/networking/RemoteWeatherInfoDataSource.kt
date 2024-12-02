package com.amit.frost.home.data.networking

import com.amit.frost.BuildConfig
import com.amit.frost.core.data.networking.constructUrl
import com.amit.frost.core.data.networking.safeCall
import com.amit.frost.core.domain.util.NetworkError
import com.amit.frost.core.domain.util.Result
import com.amit.frost.core.domain.util.map
import com.amit.frost.home.data.mapper.toWeatherInfo
import com.amit.frost.home.data.networking.dto.WeatherInfoResponseDto
import com.amit.frost.home.domain.WeatherInfo
import com.amit.frost.home.domain.WeatherInfoDataSource
import io.ktor.client.HttpClient
import io.ktor.client.request.get
import io.ktor.client.request.parameter

class RemoteWeatherInfoDataSource(
    private val httpClient: HttpClient
) : WeatherInfoDataSource {
    override suspend fun getWeatherByLatLon(
        lat: Double, lon: Double
    ): Result<WeatherInfo, NetworkError> {
        return safeCall<WeatherInfoResponseDto> {
            httpClient.get(
                urlString = constructUrl("/weather")
            ) {
                parameter("lat", lat)
                parameter("lon", lon)
                parameter("appid", BuildConfig.API_KEY)
                parameter("units", "metric")
            }
        }.map { response -> response.toWeatherInfo() }
    }
}
