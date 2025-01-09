package com.example.sandbox_compose.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class RegisterRequest(
       val email: String,
       val password: String,
       val name: String
)
