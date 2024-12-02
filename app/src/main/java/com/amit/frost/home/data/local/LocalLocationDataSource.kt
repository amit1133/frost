package com.amit.frost.home.data.local

import android.annotation.SuppressLint
import android.content.Context
import com.amit.frost.core.domain.LatLon
import com.amit.frost.home.domain.LocationDataSource
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices

import kotlinx.coroutines.tasks.await

class LocalLocationDataSource(context: Context) : LocationDataSource {
    private val fusedLocationClient: FusedLocationProviderClient =
        LocationServices.getFusedLocationProviderClient(context)

    @SuppressLint("MissingPermission")
    override suspend fun getCurrentLocation(): LatLon? {
        val location = fusedLocationClient.lastLocation.await()
        return location?.let { LatLon(it.latitude, it.longitude) }
    }
}