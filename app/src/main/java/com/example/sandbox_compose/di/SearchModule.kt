package com.example.sandbox_compose.di

import com.example.sandbox_compose.core.data.local.NewsyArticleDatabase
import com.example.sandbox_compose.core.data.mapper.Mappers
import com.example.sandbox_compose.core.data.remote.ApiService
import com.example.sandbox_compose.feature.search.data.local.SearchDto
import com.example.sandbox_compose.feature.search.data.mapper.SearchMapper
import com.example.sandbox_compose.feature.search.data.repository.SearchRepositoryImpl
import com.example.sandbox_compose.feature.search.domain.model.SearchArticle
import com.example.sandbox_compose.feature.search.domain.repository.SearchRepository
import com.example.sandbox_compose.feature.search.domain.usecase.FetchSearchArticleUseCase
import com.example.sandbox_compose.feature.search.domain.usecase.SearchUseCases
import com.example.sandbox_compose.feature.search.domain.usecase.UpdateFavouriteUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object SearchModule {

    @Provides
    @Singleton
    fun provideSearchMapper(): Mappers<SearchDto, SearchArticle> =
           SearchMapper()



    @Provides
    @Singleton
    fun provideSearchRepository(
           database: NewsyArticleDatabase,
           searchMapper: Mappers<SearchDto, SearchArticle>,
           searchApi: ApiService,
    ): SearchRepository =
           SearchRepositoryImpl(
                  api = searchApi,
                  database = database,
                  mapper = searchMapper
           )

    @Provides
    @Singleton
    fun provideSearchUseCases(
           repository: SearchRepository,
    ): SearchUseCases = SearchUseCases(
           fetchSearchArticleUseCase = FetchSearchArticleUseCase(repository),
           updateFavouriteUseCase = UpdateFavouriteUseCase(repository)
    )
}
