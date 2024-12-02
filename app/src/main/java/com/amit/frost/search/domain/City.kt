package com.amit.frost.search.domain

import kotlinx.serialization.Serializable

@Serializable
data class City(
    val id: Int,
    val name: String,
    val state: String,
    val country: String,
    val coord: Coord
)
