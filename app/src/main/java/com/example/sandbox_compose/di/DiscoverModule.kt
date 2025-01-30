package com.example.sandbox_compose.di

import com.example.sandbox_compose.core.data.local.NewsyArticleDatabase
import com.example.sandbox_compose.core.data.mapper.Mappers
import com.example.sandbox_compose.core.data.remote.ApiService
import com.example.sandbox_compose.feature.discover.data.local.DiscoverArticleDao
import com.example.sandbox_compose.feature.discover.data.local.DiscoverArticleDto
import com.example.sandbox_compose.feature.discover.data.local.DiscoverRemoteKeyDao
import com.example.sandbox_compose.feature.discover.data.mapper.DiscoverMapper
import com.example.sandbox_compose.feature.discover.data.repository.DiscoverRepoImpl
import com.example.sandbox_compose.feature.discover.domain.repository.DiscoverRepository
import com.example.sandbox_compose.feature.discover.domain.usecase.DiscoverUseCases
import com.example.sandbox_compose.feature.discover.domain.usecase.FetchDiscoverArticleUseCase
import com.example.sandbox_compose.feature.discover.domain.usecase.GetDiscoverCurrentCategoryUseCase
import com.example.sandbox_compose.feature.discover.domain.usecase.UpdateCurrentCategoryUseCase
import com.example.sandbox_compose.feature.discover.domain.usecase.UpdateFavouriteDiscoverArticleUseCase
import com.example.sandbox_compose.feature.headline.domain.model.NewsyArticle
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DiscoverModule {

    @Provides
    @Singleton
    fun provideDiscoverRepository(
           api: ApiService,
           database: NewsyArticleDatabase,
           mapper: Mappers<DiscoverArticleDto, NewsyArticle>,
    ): DiscoverRepository {
        return DiscoverRepoImpl(
               discoverApi = api,
               database = database,
               mapper = mapper
        )
    }

    @Provides
    @Singleton
    fun provideDiscoverArticleDao(
           database: NewsyArticleDatabase,
    ): DiscoverArticleDao = database.discoverArticleDao()

    @Provides
    @Singleton
    fun provideRemoteKeyDao(database: NewsyArticleDatabase): DiscoverRemoteKeyDao =
           database.discoverRemoteKeyDao()

    @Provides
    @Singleton
    fun provideDiscoverMapper(): Mappers<DiscoverArticleDto, NewsyArticle> =
           DiscoverMapper()

    @Provides
    @Singleton
    fun provideDiscoverUseCases(repository: DiscoverRepository): DiscoverUseCases {
        return DiscoverUseCases(
               fetchDiscoverArticleUseCase = FetchDiscoverArticleUseCase(repository),
               updateCurrentCategoryUseCase = UpdateCurrentCategoryUseCase(repository),
               getDiscoverCurrentCategoryUseCase = GetDiscoverCurrentCategoryUseCase(repository),
               updateFavouriteDiscoverArticleUseCase = UpdateFavouriteDiscoverArticleUseCase(repository)
        )
    }

}
