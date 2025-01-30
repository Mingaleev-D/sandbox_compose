package com.example.sandbox_compose.feature.discover.domain.usecase

import com.example.sandbox_compose.feature.discover.domain.repository.DiscoverRepository

class UpdateCurrentCategoryUseCase(
       private val repository: DiscoverRepository,
) {
    suspend operator fun invoke(category: String) {
        repository.updateCategory(category)
    }
}
