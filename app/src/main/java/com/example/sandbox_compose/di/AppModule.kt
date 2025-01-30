package com.example.sandbox_compose.di

import android.content.Context
import androidx.room.Room
import com.example.sandbox_compose.core.data.mapper.Mappers
import com.example.sandbox_compose.feature.headline.data.local.HeadlineDao
import com.example.sandbox_compose.feature.headline.data.local.HeadlineDto
import com.example.sandbox_compose.feature.headline.data.local.HeadlineRemoteKeyDao
import com.example.sandbox_compose.feature.headline.data.local.NewsyArticleDatabase
import com.example.sandbox_compose.feature.headline.data.mapper.ArticleHeadlineDtoMapper
import com.example.sandbox_compose.feature.headline.data.mapper.HeadlineMapper
import com.example.sandbox_compose.core.data.model.ArticleDTO
import com.example.sandbox_compose.core.data.remote.ApiService
import com.example.sandbox_compose.feature.headline.data.repository.HeadlineRepositoryImpl
import com.example.sandbox_compose.feature.headline.domain.model.NewsyArticle
import com.example.sandbox_compose.feature.headline.domain.repository.HeadlineRepository
import com.example.sandbox_compose.feature.headline.domain.usecase.FetchHeadlineArticleUseCase
import com.example.sandbox_compose.feature.headline.domain.usecase.HeadlineUseCases
import com.example.sandbox_compose.feature.headline.domain.usecase.UpdateHeadlineFavouriteUseCase
import com.example.sandbox_compose.utils.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    // =============== remote =================
    @Provides
    fun provideBaseUrl(): String {
        return Constants.BASE_URL
    }

    @Provides
    @Singleton
    fun provideLoggingInterceptor(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
    }

    @Provides
    @Singleton
    fun provideOkHttpClient(logger: HttpLoggingInterceptor): OkHttpClient {
        val okHttpClient = OkHttpClient.Builder()
        okHttpClient.addInterceptor(logger)
        okHttpClient.callTimeout(30, TimeUnit.SECONDS)
        okHttpClient.connectTimeout(30, TimeUnit.SECONDS)
        okHttpClient.writeTimeout(30, TimeUnit.SECONDS)
        okHttpClient.readTimeout(30, TimeUnit.SECONDS)
        val okHttp = okHttpClient.build()
        return okHttp
    }

    @Provides
    @Singleton
    fun provideConvectorFactory(): GsonConverterFactory {
        return GsonConverterFactory.create()
    }

    @Provides
    @Singleton
    fun provideRetrofit(
           baseUrl: String,
           gsonFactory: GsonConverterFactory,
           okHttpClient: OkHttpClient
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(gsonFactory)
            .client(okHttpClient)
            .build()
    }

    @Provides
    @Singleton
    fun provideApiService(retrofit: Retrofit): ApiService {
        return retrofit.create(ApiService::class.java)
    }

    // =====================================================
    //  ================= repository =======================
    @Provides
    @Singleton
    fun provideHeadlineRepository(
           api: ApiService,
           database: NewsyArticleDatabase,
           mapper: Mappers<HeadlineDto, NewsyArticle>,
           articleHeadlineMapper: Mappers<ArticleDTO, HeadlineDto>,
    ): HeadlineRepository {
        return HeadlineRepositoryImpl(
               headlineApi = api,
               database = database,
               mapper = mapper,
               articleHeadlineMapper = articleHeadlineMapper
        )
    }

    //====================================================================
    // ============================= usecase ============================
    @Provides
    @Singleton
    fun provideHeadlineUseCases(
           repository: HeadlineRepository,
    ): HeadlineUseCases =
           HeadlineUseCases(
                  fetchHeadlineArticleUseCase = FetchHeadlineArticleUseCase(
                         repository = repository
                  ),
                  updateHeadlineFavouriteUseCase = UpdateHeadlineFavouriteUseCase(
                         repository = repository
                  )
           )

    //===== ===========================================================
    // =========================== database ===========================
    @Singleton
    @Provides
    fun provideNewsyDatabase(
           @ApplicationContext context: Context,
    ): NewsyArticleDatabase {
        return Room.databaseBuilder(
               context,
               NewsyArticleDatabase::class.java,
               "newsy_db"
        )
            .build()
    }

    @Provides
    @Singleton
    fun provideHeadlineDao(
           database: NewsyArticleDatabase,
    ): HeadlineDao = database.headlineDao()

    @Provides
    @Singleton
    fun provideRemoteKeyDao(
           database: NewsyArticleDatabase,
    ): HeadlineRemoteKeyDao = database.headlineRemoteDao()

    //======================================================================
    // ============================= mapper ================================
    @Provides
    @Singleton
    fun provideArticleToHeadlineMapper(): Mappers<ArticleDTO, HeadlineDto> = ArticleHeadlineDtoMapper()

    @Provides
    @Singleton
    fun provideHeadlineMapper(): Mappers<HeadlineDto, NewsyArticle> = HeadlineMapper()
    // ==========================================================================
}
