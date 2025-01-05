package com.example.sandbox_compose.data.model

import com.example.sandbox_compose.domain.model.Category
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RemoteCategoriesData(
       @SerialName("id")
       val id: Int = 0,
       @SerialName("image")
       val image: String = "",
       @SerialName("title")
       val title: String = ""
) {

    fun toCategory() = Category(
           id = id,
           image = image,
           title = title
    )
}
