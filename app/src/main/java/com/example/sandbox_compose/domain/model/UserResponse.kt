package com.example.sandbox_compose.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class UserResponse(
       val id: Int?,
       val username: String,
       val email: String,
       val name: String
) {
    fun toDomainModel() = UserDomainModel(
           id = id,
           username = username,
           email = email,
           name = name
    )
}

@Serializable
data class UserAuthResponse(
       val `data`: UserResponse,
       val msg: String
)
