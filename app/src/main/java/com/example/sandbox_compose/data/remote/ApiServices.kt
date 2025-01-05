package com.example.sandbox_compose.data.remote

import com.example.sandbox_compose.data.model.RemoteCategories
import com.example.sandbox_compose.data.model.RemoteProducts
import com.example.sandbox_compose.domain.model.CategoriesListModel
import com.example.sandbox_compose.domain.model.ProductListModel
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.ClientRequestException
import io.ktor.client.plugins.ServerResponseException
import io.ktor.client.request.header
import io.ktor.client.request.request
import io.ktor.http.ContentType
import io.ktor.http.HttpMethod
import io.ktor.http.Parameters
import io.ktor.http.contentType
import io.ktor.utils.io.InternalAPI
import io.ktor.utils.io.errors.IOException

// private val baseUrl = "https://fakestoreapi.com"
//val baseUrl = "https://fakestores.onrender.com/api/"
val baseUrl = "https://ecommerce-ktor-4641e7ff1b63.herokuapp.com/"

class ApiServicesImpl(val client: HttpClient) : ApiService {

    override suspend fun getProducts(category: Int?): ResultWrapper<ProductListModel> {
        val url = if (category != null) "${baseUrl}products/category/$category" else "${baseUrl}products"

        return makeWebRequest(
               url = url,
               method = HttpMethod.Get,
               mapper = { dataModels: RemoteProducts ->
                   dataModels.toProductList()
               })
    }

    override suspend fun getCategories(): ResultWrapper<CategoriesListModel> {
        val url = "${baseUrl}categories"

        return makeWebRequest(
               url = url,
               method = HttpMethod.Get,
               mapper = { categories: RemoteCategories ->
                   categories.toCategoriesList()
               })
    }

    @OptIn(InternalAPI::class)
    suspend inline fun <reified T, R> makeWebRequest(
           url: String,
           method: HttpMethod,
           body: Any? = null,
           headers: Map<String, String> = emptyMap(),
           parameters: Map<String, String> = emptyMap(),
           noinline mapper: ((T) -> R)? = null
    ): ResultWrapper<R> {
        return try {
            val response = client.request(url) {
                this.method = method
                // Apply query parameters
                url {
                    this.parameters.appendAll(Parameters.build {
                        parameters.forEach { (key, value) ->
                            append(key, value)
                        }
                    })
                }
                // Apply headers
                headers.forEach { (key, value) ->
                    header(key, value)
                }
                // Set body for POST, PUT, etc.
                if (body != null) {
                    this.body = body
                }
                // Set content type
                contentType(ContentType.Application.Json)
            }.body<T>()
            val result = mapper?.invoke(response) ?: response as R
            ResultWrapper.Success(result)
        } catch (e: ClientRequestException) {
            ResultWrapper.Failure(e)
        } catch (e: ServerResponseException) {
            ResultWrapper.Failure(e)
        } catch (e: IOException) {
            ResultWrapper.Failure(e)
        } catch (e: Exception) {
            ResultWrapper.Failure(e)
        }
    }
}

interface ApiService {

    suspend fun getProducts(category: Int?): ResultWrapper<ProductListModel>
    suspend fun getCategories(): ResultWrapper<CategoriesListModel>
}

sealed class ResultWrapper<out T> {
    data class Success<out T>(val value: T) : ResultWrapper<T>()
    data class Failure(val exception: Exception) : ResultWrapper<Nothing>()
}
