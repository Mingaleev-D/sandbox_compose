package com.example.sandbox_compose.domain.usecase

import com.example.sandbox_compose.domain.repository.UserRepository

class LoginUseCase(private val userRepository: UserRepository) {
    suspend fun execute(username: String, password: String) =
           userRepository.login(username, password)
}
