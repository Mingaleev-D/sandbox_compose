package com.example.sandbox_compose.core.data.model


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SourceDTO(
    @SerialName("id")
    val id: String? = null,
    @SerialName("name")
    val name: String? = null
)
