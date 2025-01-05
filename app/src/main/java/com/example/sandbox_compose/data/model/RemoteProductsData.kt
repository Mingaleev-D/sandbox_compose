package com.example.sandbox_compose.data.model


import com.example.sandbox_compose.domain.model.Product
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RemoteProductsData(
    @SerialName("categoryId")
    val categoryId: Int = 0,
    @SerialName("description")
    val description: String = "",
    @SerialName("id")
    val id: Int = 0,
    @SerialName("image")
    val image: String = "",
    @SerialName("price")
    val price: Double = 0.0,
    @SerialName("title")
    val title: String = ""
){
    fun toProduct() = Product(
           id = id,
           title = title,
           price = price,
           categoryId = categoryId,
           description = description,
           image = image
    )
}
