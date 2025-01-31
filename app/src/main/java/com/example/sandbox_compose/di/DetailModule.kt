package com.example.sandbox_compose.di

import com.example.sandbox_compose.core.data.local.NewsyArticleDatabase
import com.example.sandbox_compose.feature.details.data.local.DetailDao
import com.example.sandbox_compose.feature.details.data.repository.DetailRepositoryImpl
import com.example.sandbox_compose.feature.details.domain.repository.DetailRepository
import com.example.sandbox_compose.feature.details.domain.usecase.DetailUseCases
import com.example.sandbox_compose.feature.details.domain.usecase.GetDetailDiscoverArticleUseCase
import com.example.sandbox_compose.feature.details.domain.usecase.GetDetailHeadlineArticleUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DetailModule {
    @Provides
    @Singleton
    fun provideDetailRepository(
           detailDao: DetailDao,
    ): DetailRepository =
           DetailRepositoryImpl(detailDao)

    @Provides
    @Singleton
    fun provideDetailDao(
           newsyArticleDatabase: NewsyArticleDatabase,
    ): DetailDao = newsyArticleDatabase.detailDao()

    @Provides
    @Singleton
    fun provideDetailUsecases(repo: DetailRepository): DetailUseCases = DetailUseCases(
           getDetailDiscoverArticleUseCase = GetDetailDiscoverArticleUseCase(
                  repo
           ),
           getDetailHeadlineArticleUseCase = GetDetailHeadlineArticleUseCase(
                  repo
           ),
//           getDetailSearchArticleUseCase = GetDetailSearchArticleUseCase(
//                  repo
//           )

    )

}
