package com.example.sandbox_compose.feature.headline.domain.usecase

data class HeadlineUseCases(
       val fetchHeadlineArticleUseCase: FetchHeadlineArticleUseCase,
       val updateHeadlineFavouriteUseCase: UpdateHeadlineFavouriteUseCase,
)
