package com.example.sandbox_compose.domain.model

import androidx.annotation.DrawableRes
import com.example.sandbox_compose.R

data class OnboardUiPageModel(
       val title: String,
       val description: String,
       @DrawableRes val image: Int
)

val pages = listOf(
       OnboardUiPageModel(
              title = "Lorem Ipsum is simply dummy",
              description = "Lorem Ipsum is simply dummy text of the printing and typesetting industry.",
              image = R.drawable.onboarding1
       ),
       OnboardUiPageModel(
              title = "Lorem Ipsum is simply dummy",
              description = "Lorem Ipsum is simply dummy text of the printing and typesetting industry.",
              image = R.drawable.onboarding2
       ),
       OnboardUiPageModel(
              title = "Lorem Ipsum is simply dummy",
              description = "Lorem Ipsum is simply dummy text of the printing and typesetting industry.",
              image = R.drawable.onboarding3
       )
)
