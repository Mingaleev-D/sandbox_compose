package com.example.sandbox_compose.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ProductsItem(
       @SerialName("availability")
       val availability: String = "",
       @SerialName("category")
       val category: String = "",
       @SerialName("description")
       val description: String = "",
       @SerialName("id")
       val id: String = "",
       @SerialName("imageURL")
       val imageURL: String? = "",
       @SerialName("price")
       val price: Double = 0.0,
       @SerialName("productType")
       val productType: String = "",
       @SerialName("rating")
       val rating: Double = 0.0,
       @SerialName("ratingCount")
       val ratingCount: Int = 0,
       @SerialName("seller")
       val seller: String = "",
       @SerialName("source")
       val source: String = "",
       @SerialName("title")
       val title: String = ""
)

@Serializable
class DataProductModel(
       val id: String,
       val title: String,
       val price: Double,
       val category: String,
       val description: String,
       val imageURL: String
) {

    fun toProduct() = ProductsItem(
           id = id.toString(),
           title = title,
           price = price,
           category = category,
           description = description,
           imageURL = imageURL
    )
}
