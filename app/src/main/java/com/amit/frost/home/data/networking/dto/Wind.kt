package com.amit.frost.home.data.networking.dto


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Wind(
    @SerialName("deg")
    val deg: Int = 0,
    @SerialName("speed")
    val speed: Double = 0.0
)