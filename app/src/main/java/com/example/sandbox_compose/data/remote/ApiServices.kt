package com.example.sandbox_compose.data.remote

import com.example.sandbox_compose.data.model.DataProductModel
import com.example.sandbox_compose.data.model.ProductsItem
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

class ApiServicesImpl(val client: HttpClient) : ApiService {

    override suspend fun getProducts(): ResultWrapper<List<ProductsItem>> {
        return makeWebRequest(
               url = "https://fakestoreapi.com/products",
               method = HttpMethod.Get,
               mapper = { dataModels: List<DataProductModel> ->
                   dataModels.map { it.toProduct() }
               }
        )
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
            val result: R = mapper?.invoke(response) ?: response as R
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

    suspend fun getProducts(): ResultWrapper<List<ProductsItem>>
}

sealed class ResultWrapper<out T> {
    data class Success<out T>(val value: T) : ResultWrapper<T>()
    data class Failure(val exception: Exception) : ResultWrapper<Nothing>()
}
