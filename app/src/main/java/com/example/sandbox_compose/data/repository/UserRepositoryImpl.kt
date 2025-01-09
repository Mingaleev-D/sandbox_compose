package com.example.sandbox_compose.data.repository

import com.example.sandbox_compose.data.remote.ApiService
import com.example.sandbox_compose.domain.repository.UserRepository

class UserRepositoryImpl(private val apiService: ApiService) : UserRepository {
    override suspend fun register(email: String, password: String, name: String) =
           apiService.register(email, password, name)

    override suspend fun login(email: String, password: String) =
           apiService.login(email, password)
}
