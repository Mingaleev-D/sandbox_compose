package com.example.sandbox_compose.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class LoginRequest(
       val email: String,
       val password: String
)
