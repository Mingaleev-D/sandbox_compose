package com.example.sandbox_compose.domain.repository

import kotlinx.coroutines.flow.Flow

interface LocalUserManagerRepository {

    suspend fun saveAppEntry()
    fun readAppEntry(): Flow<Boolean>
}
