package com.example.sandbox_compose.feature.discover.domain.usecase

data class DiscoverUseCases(
       val fetchDiscoverArticleUseCase: FetchDiscoverArticleUseCase,
       val updateCurrentCategoryUseCase: UpdateCurrentCategoryUseCase,
       val getDiscoverCurrentCategoryUseCase: GetDiscoverCurrentCategoryUseCase,
       val updateFavouriteDiscoverArticleUseCase: UpdateFavouriteDiscoverArticleUseCase,
)
