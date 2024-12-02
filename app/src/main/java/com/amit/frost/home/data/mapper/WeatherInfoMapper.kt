package com.amit.frost.home.data.mapper

import com.amit.frost.home.data.networking.dto.WeatherInfoResponseDto
import com.amit.frost.home.domain.WeatherInfo
import java.time.Instant
import java.time.ZoneId


fun WeatherInfoResponseDto.toWeatherInfo(): WeatherInfo {
    return WeatherInfo(
        city = name,
        lon = coord.lon,
        lat = coord.lat,
        icon = weather.first().icon,
        weatherStatus = weather.first().main,
        currentTemperature = main.temp.toInt(),
        feelsLike = main.feelsLike.toInt(),
        minTemperature = main.tempMin.toInt(),
        maxTemperature = main.tempMax.toInt(),
        humidity = main.humidity,
        pressure = main.pressure,
        windSpeed = wind.speed,
        sunRise = Instant.ofEpochSecond(sys.sunrise)
            .atZone(ZoneId.of("UTC")),
        sunSet = Instant.ofEpochSecond(sys.sunset)
            .atZone(ZoneId.of("UTC")),
    )
}
