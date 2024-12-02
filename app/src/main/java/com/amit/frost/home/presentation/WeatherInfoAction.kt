package com.amit.frost.home.presentation

sealed interface WeatherInfoAction {
    data object OnSearchClick : WeatherInfoAction
}