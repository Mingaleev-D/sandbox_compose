package com.example.sandbox_compose.data.model


import com.example.sandbox_compose.domain.model.CategoriesListModel
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RemoteCategories(
       @SerialName("data")
    val `data`: List<RemoteCategoriesData> = listOf(),
       @SerialName("msg")
    val msg: String = ""
){
    fun toCategoriesList() = CategoriesListModel(
           categories = `data`.map { it.toCategory() },
           msg = msg
    )
}
