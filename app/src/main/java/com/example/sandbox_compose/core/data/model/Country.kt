package com.example.sandbox_compose.core.data.model

import androidx.annotation.DrawableRes

data class Country(
       val code: String,
       val name: String,
       @DrawableRes val icResId: Int,
)
