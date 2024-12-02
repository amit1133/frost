package com.amit.frost.home.domain

import com.amit.frost.core.domain.LatLon

interface LocationDataSource {
    suspend fun getCurrentLocation(): LatLon?
}