package com.amit.frost.search.domain

interface CityDataSource {
    suspend fun getCities(): List<City>
}