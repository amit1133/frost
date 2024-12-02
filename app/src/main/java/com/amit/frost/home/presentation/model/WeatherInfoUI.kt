package com.amit.frost.home.presentation.model

import com.amit.frost.BuildConfig
import com.amit.frost.home.domain.WeatherInfo
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter

data class WeatherInfoUI(
    val city: String,
    val lon: Double,
    val lat: Double,
    val iconUrl: DisplayableImageURL,
    val weatherStatus: String,
    val currentTemperature: DisplayableNumber,
    val feelsLike: DisplayableNumber,
    val minTemperature: DisplayableNumber,
    val maxTemperature: DisplayableNumber,
    val humidity: DisplayableNumber,
    val pressure: DisplayableNumber,
    val windSpeed: DisplayableNumber,
    val sunRise: DisplayableDateTime,
    val sunSet: DisplayableDateTime,
)

data class DisplayableNumber(
    val value: Int, val formatted: String
)

data class DisplayableDateTime(
    val value: ZonedDateTime, val formatted: String
)

data class DisplayableImageURL(
    val value: String,
    val smallImageURL: String,
    val mediumImageURL: String,
    val bigImageURL: String,
)

fun String.toImageDisplayable(): DisplayableImageURL = DisplayableImageURL(
    value = this,
    smallImageURL = BuildConfig.ICON_URL + this + ".png",
    mediumImageURL = BuildConfig.ICON_URL + this + "@2x.png",
    bigImageURL = BuildConfig.ICON_URL + this + "@4x.png",
)

fun Int.toTemperatureDisplayable(): DisplayableNumber = DisplayableNumber(
    value = this, formatted = "$this°"
)

fun Int.toWindSpeedDisplayable(): DisplayableNumber = DisplayableNumber(
    value = this, formatted = "$this m/s"
)

fun Int.toHumidityDisplayable(): DisplayableNumber = DisplayableNumber(
    value = this, formatted = "$this %"
)

fun Int.toPressureDisplayable(): DisplayableNumber = DisplayableNumber(
    value = this, formatted = "$this hPa"
)

fun ZonedDateTime.toDateTimeDisplayable(): DisplayableDateTime {
    val formatter = DateTimeFormatter.ofPattern("HH:mm:ss a")
    return DisplayableDateTime(
        value = this, formatted = this.toLocalTime().format(formatter)
    )
}


fun WeatherInfo.toWeatherInfoUi(): WeatherInfoUI {
    return WeatherInfoUI(
        city = city,
        lon = lon,
        lat = lat,
        iconUrl = icon.toImageDisplayable(),
        weatherStatus = weatherStatus,
        currentTemperature = currentTemperature.toTemperatureDisplayable(),
        feelsLike = feelsLike.toTemperatureDisplayable(),
        minTemperature = minTemperature.toTemperatureDisplayable(),
        maxTemperature = maxTemperature.toTemperatureDisplayable(),
        humidity = humidity.toHumidityDisplayable(),
        pressure = pressure.toPressureDisplayable(),
        windSpeed = windSpeed.toInt().toWindSpeedDisplayable(),
        sunRise = sunRise.toDateTimeDisplayable(),
        sunSet = sunSet.toDateTimeDisplayable(),
    )
}

internal val previewWeatherInfo = WeatherInfo(
    city = "Dhaka",
    lon = 12.2,
    lat = 12.2,
    icon = "10d",
    weatherStatus = "Cloudy",
    currentTemperature = 20,
    feelsLike = 22,
    minTemperature = 22,
    maxTemperature = 28,
    humidity = 32,
    pressure = 212,
    windSpeed = 22.0,
    sunRise = ZonedDateTime.now(),
    sunSet = ZonedDateTime.now(),
).toWeatherInfoUi()
