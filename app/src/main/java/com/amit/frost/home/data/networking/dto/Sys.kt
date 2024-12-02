package com.amit.frost.home.data.networking.dto


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Sys(
    @SerialName("country")
    val country: String = "",
    @SerialName("id")
    val id: Int = 0,
    @SerialName("sunrise")
    val sunrise: Long = 0,
    @SerialName("sunset")
    val sunset: Long = 0,
    @SerialName("type")
    val type: Int = 0
)