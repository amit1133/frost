package com.amit.frost.home.presentation

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.amit.frost.core.domain.LatLon
import com.amit.frost.core.domain.LatLonDataSource
import com.amit.frost.core.domain.util.onError
import com.amit.frost.core.domain.util.onSuccess
import com.amit.frost.home.domain.LocationDataSource
import com.amit.frost.home.domain.WeatherInfoDataSource
import com.amit.frost.home.presentation.model.toWeatherInfoUi
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class WeatherInfoViewModel(
    private val source: WeatherInfoDataSource,
    private val localDataStore: LatLonDataSource,
    private val locationDataSource: LocationDataSource,

    ) : ViewModel() {
    private val _state = MutableStateFlow(WeatherInfoState())
    val state = _state.asStateFlow()

    init {
        viewModelScope.launch {
            localDataStore.getLatLon().collect { latLon ->
                _state.update { it.copy(latLon = latLon) }
                loadWeatherInfo(latLon.lat, latLon.lon)
            }
        }
    }

    private val _events = Channel<WeatherInfoEvent>()
    val events = _events.receiveAsFlow()

    fun onAction(action: WeatherInfoAction) {
        when (action) {
            is WeatherInfoAction.OnSearchClick -> {
                Log.d("WeatherInfoViewModel", "onAction: Search Clicked ")
            }
        }
    }

    private fun loadWeatherInfo(lat: Double, lon: Double) {
        viewModelScope.launch {
            _state.update {
                it.copy(
                    isLoading = true
                )
            }

            source
                .getWeatherByLatLon(
                    lat = lat,
                    lon = lon
                )
                .onSuccess { weatherInfo ->
                    _state.update {
                        it.copy(
                            isLoading = false,
                            info = weatherInfo.toWeatherInfoUi()
                        )
                    }
                }
                .onError { error ->
                    _state.update { it.copy(isLoading = false) }
                    _events.send(WeatherInfoEvent.Error(error))
                }
        }
    }

    fun updatePermissionStatus(hasPermission: Boolean) {
        _state.value = _state.value.copy(hasLocationPermission = hasPermission)
    }

    fun fetchLocation() {
        if (!_state.value.hasLocationPermission) return
        viewModelScope.launch {
            _state.value = _state.value.copy(isLoading = true)
            val latLon = locationDataSource.getCurrentLocation()
            _state.value = _state.value.copy(latLon = latLon, isLoading = false)
            if (latLon != null) {
                localDataStore.saveLatLon(LatLon(latLon.lat, latLon.lon))
            }
        }
    }
}