package com.example.sandbox_compose.di

import com.example.sandbox_compose.data.remote.ApiService
import com.example.sandbox_compose.data.remote.ApiServicesImpl
import com.example.sandbox_compose.data.repository.ProductsRepositoryImpl
import com.example.sandbox_compose.domain.repository.ProductsRepository
import com.example.sandbox_compose.domain.usecase.GetCategoriesUseCase
import com.example.sandbox_compose.domain.usecase.GetProductsUseCase
import com.example.sandbox_compose.ui.pages.cart.CartSummaryViewModel
import com.example.sandbox_compose.ui.pages.cart.CartViewModel
import com.example.sandbox_compose.ui.pages.home_products.HomeViewModel
import com.example.sandbox_compose.ui.pages.home_products.ProductDetailViewModel
import io.ktor.client.HttpClient
import io.ktor.client.plugins.HttpTimeout
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

//val dataModule = module {
//    includes(networkModule, repositoryModule)
//}
//val domainModule = module {
//    includes(useCaseModule)
//}

//val presentationModule = module {
//    includes(viewModelModule)
//}


val networkModule = module {
    single {
        HttpClient { // Указываем движок CIO
            install(ContentNegotiation) {
                json(Json {
                    prettyPrint = true
                    isLenient = true
                    ignoreUnknownKeys = true
                })
            }
            install(Logging) {
                logger = object : Logger {
                    override fun log(message: String) {
                        println("Ktor Log: $message")
                    }
                }
                level = LogLevel.ALL
            }
            install(HttpTimeout) {
                requestTimeoutMillis = 30000 // 10 секунд
                connectTimeoutMillis = 30000
                socketTimeoutMillis = 30000
            }
        }
    }

    single<ApiService> { ApiServicesImpl(get()) }
}
val repositoryModule = module {
    single<ProductsRepository> { ProductsRepositoryImpl(get()) }
}
val useCaseModule = module {
    factory { GetProductsUseCase(get()) }
    factory { GetCategoriesUseCase(get()) }
}
val viewModelModule = module {
    viewModel {
        HomeViewModel(get(), get())
    }
    viewModel {
        ProductDetailViewModel(get())
    }
    viewModel {
        CartViewModel(get())
    }
    viewModel {
        CartSummaryViewModel(get())
    }
}


val appModule = module {
    includes(networkModule, repositoryModule, useCaseModule, viewModelModule) // Включаем все модули
}
//class ApplicationModule {
//}
