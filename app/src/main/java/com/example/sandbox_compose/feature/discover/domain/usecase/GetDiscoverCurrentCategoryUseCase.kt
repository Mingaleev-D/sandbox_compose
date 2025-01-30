package com.example.sandbox_compose.feature.discover.domain.usecase

import com.example.sandbox_compose.feature.discover.domain.repository.DiscoverRepository

class GetDiscoverCurrentCategoryUseCase(
       private val repository: DiscoverRepository,
) {
    suspend operator fun invoke(): String {
        return repository.getDiscoverCurrentCategory()
    }
}
