package com.amit.frost.core.domain

import kotlinx.coroutines.flow.Flow

interface LatLonDataSource {
    suspend fun saveLatLon(latLon: LatLon)
    fun getLatLon(): Flow<LatLon>
}