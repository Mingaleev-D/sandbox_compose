package com.example.sandbox_compose.domain.repository

import com.example.sandbox_compose.data.remote.ResultWrapper
import com.example.sandbox_compose.domain.model.UserDomainModel

interface UserRepository {

    suspend fun login(
           email: String,
           password: String
    ): ResultWrapper<UserDomainModel>

    suspend fun register(
           email: String,
           password: String,
           name: String
    ): ResultWrapper<UserDomainModel>
}
