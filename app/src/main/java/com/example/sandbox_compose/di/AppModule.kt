package com.example.sandbox_compose.di

import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

//    @Provides
//    fun provideBaseUrl(): String {
//        return ""
//    }
//
//    @Provides
//    @Singleton
//    fun provideLoggingInterceptor(): HttpLoggingInterceptor {
//        return HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
//    }
//
//    @Provides
//    @Singleton
//    fun provideOkHttpClient(logger: HttpLoggingInterceptor): OkHttpClient {
//        val okHttpClient = OkHttpClient.Builder()
//        okHttpClient.addInterceptor(logger)
//        okHttpClient.callTimeout(30, TimeUnit.SECONDS)
//        okHttpClient.connectTimeout(30, TimeUnit.SECONDS)
//        okHttpClient.writeTimeout(30, TimeUnit.SECONDS)
//        okHttpClient.readTimeout(30, TimeUnit.SECONDS)
//        val okHttp = okHttpClient.build()
//        return okHttp
//    }
//
//    @Provides
//    @Singleton
//    fun provideConvectorFactory(): GsonConverterFactory {
//        return GsonConverterFactory.create()
//    }
//
//    @Provides
//    @Singleton
//    fun provideRetrofit(
//           baseUrl: String,
//           gsonFactory: GsonConverterFactory,
//           okHttpClient: OkHttpClient
//    ): Retrofit {
//        return Retrofit.Builder()
//            .baseUrl(baseUrl)
//            .addConverterFactory(gsonFactory)
//            .client(okHttpClient)
//            .build()
//    }
//
//    @Provides
//    @Singleton
//    fun provideApiService(retrofit: Retrofit): ApiService {
//        return retrofit.create(ApiService::class.java)
//    }

//    @Provides
//    @Singleton
//    fun provideRepository(
//           apiService: ApiService,
//    ): MovieRepository {
//        return MovieRepositoryImpl(
//               apiService = apiService,
//        )
//    }
}
