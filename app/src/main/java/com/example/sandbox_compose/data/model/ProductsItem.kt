package com.example.sandbox_compose.data.model


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ProductsItem(
    @SerialName("category")
    val category: String = "",
    @SerialName("description")
    val description: String = "",
    @SerialName("id")
    val id: Int = 0,
    @SerialName("image")
    val image: String = "",
    @SerialName("price")
    val price: Double = 0.0,
    @SerialName("rating")
    val rating: Rating = Rating(),
    @SerialName("title")
    val title: String = "",
)

@Serializable
enum class Category(val value: String) {
    @SerialName("electronics") Electronics("electronics"),
    @SerialName("jewelery") Jewelery("jewelery"),
    @SerialName("men's clothing") MenSClothing("men's clothing"),
    @SerialName("women's clothing") WomenSClothing("women's clothing");
}

@Serializable
data class DataProductModel(
       val id: Int,
       val title: String,
       val price: Double,
       val description: String,
       val category: String,
       val image: String,
      // val rating: Rating
){
    fun toProduct(): ProductsItem {
        return ProductsItem(
               id = id,
               title = title,
               price = price,
               description = description,
               category = category,
               image = image,
              // rating = rating
        )
    }
}
