package com.amit.frost.home.presentation

import com.amit.frost.core.domain.util.NetworkError

sealed interface WeatherInfoEvent {
    data class Error(val error: NetworkError) : WeatherInfoEvent
}