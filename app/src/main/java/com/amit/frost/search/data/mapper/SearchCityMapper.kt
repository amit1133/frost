package com.amit.frost.search.data.mapper

import com.amit.frost.search.domain.City
import com.amit.frost.search.presentation.model.CityData
import com.amit.frost.search.presentation.model.SearchUI


fun City.toCityData(): CityData {
    return CityData(
        city = name, lon = coord.lon, lat = coord.lat
    )
}

fun convertListToSearchUI(cityDataList: List<CityData>): SearchUI =
    SearchUI(cities = cityDataList)

