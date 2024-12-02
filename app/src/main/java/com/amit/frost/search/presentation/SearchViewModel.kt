package com.amit.frost.search.presentation

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.amit.frost.core.domain.LatLon
import com.amit.frost.core.domain.LatLonDataSource
import com.amit.frost.search.data.asset.AssetCityDataSource
import com.amit.frost.search.data.mapper.convertListToSearchUI
import com.amit.frost.search.data.mapper.toCityData
import com.amit.frost.search.presentation.model.CityData
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch


class SearchViewModel(
    private val source: AssetCityDataSource,
    private val localDataStore: LatLonDataSource
) : ViewModel() {

    private val _state = MutableStateFlow(SearchState())
    val state = _state.onStart { loadZilaFromJSON() }.stateIn(
        viewModelScope, SharingStarted.WhileSubscribed(5000L), SearchState()
    )

    fun onSearchQueryChange(query: String) {
        viewModelScope.launch {
            _state.update {
                it.copy(
                    isLoading = false, query = query
                )
            }

            val results = filterData(query)
//                val allCityNames = results.joinToString(", ") { it.city }
//                Log.d("TAG", allCityNames)
            _state.update {
                it.copy(
                    cities = results
                )
            }
        }
    }

    fun saveCityLatLon(lat: Double, lon: Double) {
        Log.d("TAG", "saveCityLatLon: ")
        viewModelScope.launch {
            localDataStore.saveLatLon(LatLon(lat, lon))
        }
    }


    private fun filterData(query: String): List<CityData> {
        return if (query.isBlank()) {
            emptyList()
        } else {
            state.value.searchUI?.cities!!.filter { it.city.contains(query, ignoreCase = true) }
        }
    }

    private fun loadZilaFromJSON() {
        viewModelScope.launch {
            _state.update {
                it.copy(
                    isLoading = true
                )
            }
            _state.update {
                it.copy(
                    isLoading = false,
                    searchUI = convertListToSearchUI(
                        source.getCities().map { city -> city.toCityData() })
                )
            }
            Log.d("TAG", "loadZilaFromJSON: ${state.value.searchUI?.cities?.size}")
        }
    }


}