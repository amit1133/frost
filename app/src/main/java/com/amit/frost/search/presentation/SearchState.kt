package com.amit.frost.search.presentation

import com.amit.frost.search.presentation.model.CityData
import com.amit.frost.search.presentation.model.SearchUI

data class SearchState(
    val isLoading: Boolean = false,
    val searchUI: SearchUI? = null,
    var cities: List<CityData> = emptyList<CityData>(),
    var query: String = ""
)