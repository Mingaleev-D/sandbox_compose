package com.example.sandbox_compose.domain.usecase

import com.example.sandbox_compose.domain.repository.LocalUserManagerRepository
import javax.inject.Inject

class SaveAppEntryUseCase @Inject constructor(
       private val localUserManagerRepository: LocalUserManagerRepository
) {

    suspend operator fun invoke() = localUserManagerRepository.saveAppEntry()
}
