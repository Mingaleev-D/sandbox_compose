package com.example.sandbox_compose.domain.usecase

import com.example.sandbox_compose.domain.repository.NewsRepository
import javax.inject.Inject

class GetNewsUseCase @Inject constructor(
       private val newsRepository: NewsRepository
) {

    operator fun invoke(sources: List<String>) =
           newsRepository.getNews(sources)
}
