package com.example.sandbox_compose.data.model


import com.example.sandbox_compose.domain.model.ProductListModel
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RemoteProducts(
       @SerialName("data")
    val `data`: List<RemoteProductsData> = listOf(),
       @SerialName("msg")
    val msg: String = ""
){
    fun toProductList() = ProductListModel(
           products = `data`.map { it.toProduct() },
           msg = msg
    )
}
