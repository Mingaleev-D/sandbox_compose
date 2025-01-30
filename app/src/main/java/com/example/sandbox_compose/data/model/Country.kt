package com.example.sandbox_compose.data.model

import androidx.annotation.DrawableRes

data class Country(
       val code: String,
       val name: String,
       @DrawableRes val icResId: Int,
)
