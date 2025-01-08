package com.example.sandbox_compose.ui.navigator

import android.os.Parcelable
import com.example.sandbox_compose.domain.model.Product
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.Serializable
import android.os.Build
import android.os.Bundle
import androidx.annotation.RequiresApi
import androidx.navigation.NavType
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import java.net.URLDecoder
import java.net.URLEncoder
import java.util.Base64

class ProductNavType {
}

val productNavType = object : NavType<UiProductModel>(isNullableAllowed = false) {
    override fun get(
           bundle: Bundle,
           key: String
    ): UiProductModel? {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU)
            return bundle.getParcelable(key, UiProductModel::class.java)
        return bundle.getParcelable(key) as? UiProductModel
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun parseValue(value: String): UiProductModel {
        val item = Json.decodeFromString<UiProductModel>(value)

        return item.copy(
               image = URLDecoder.decode(item.image, "UTF-8"),
               description = String(Base64.getDecoder().decode(item.description.replace("_", "/"))),
               title = String(Base64.getDecoder().decode(item.title.replace("_", "/")))
        )
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun serializeAsValue(value: UiProductModel): String {
        return Json.encodeToString(
               value.copy(
                      image = URLEncoder.encode(value.image, "UTF-8"),
                      description = String(
                             Base64.getEncoder().encode(value.description.toByteArray())
                      ).replace("/", "_"),
                      title = String(Base64.getEncoder().encode(value.title.toByteArray())).replace(
                             "/",
                             "_"
                      )
               )
        )
    }

    override fun put(
           bundle: Bundle,
           key: String,
           value: UiProductModel
    ) {
        bundle.putParcelable(key, value)
    }
}

@Serializable
data class ProductDetails(val product: UiProductModel)

@Serializable
@Parcelize
data class UiProductModel(
       val id: Int,
       val title: String,
       val price: Double,
       val categoryId: Int,
       val description: String,
       val image: String
) : Parcelable {

    companion object {

        fun fromProduct(product: Product) = UiProductModel(
               id = product.id,
               title = product.title,
               price = product.price,
               categoryId = product.categoryId,
               description = product.description,
               image = product.image
        )
    }
}
