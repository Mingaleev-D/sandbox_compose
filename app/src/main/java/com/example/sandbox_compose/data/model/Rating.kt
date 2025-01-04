package com.example.sandbox_compose.data.model


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Rating(
    @SerialName("count")
    val count: Int = 0,
    @SerialName("rate")
    val rate: Double = 0.0
)
