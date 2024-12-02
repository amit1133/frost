package com.amit.frost.search.domain

import kotlinx.serialization.Serializable

@Serializable
data class Coord(
    val lon: Double,
    val lat: Double
)