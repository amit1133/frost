package com.amit.frost.home.domain

import java.time.ZonedDateTime

data class WeatherInfo(
    val city: String,
    val lon: Double,
    val lat: Double,
    val icon: String,
    val weatherStatus: String,
    val currentTemperature: Int,
    val feelsLike: Int,
    val minTemperature: Int,
    val maxTemperature: Int,
    val humidity: Int,
    val pressure: Int,
    val windSpeed: Double,
    val sunRise: ZonedDateTime,
    val sunSet: ZonedDateTime,
)
