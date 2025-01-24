package com.example.sandbox_compose.ui.pages.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun RecommendedComp(
       modifier: Modifier = Modifier,
       selected: FilterType,
       onFilterClick: (FilterType) -> Unit,
       ) {
    Column(
           modifier = modifier.fillMaxWidth(),
    ) {
        CategoryTitleComp(
               txt = "Recommended For You"
        )
        Spacer(modifier = Modifier.height(10.dp))

        FilterPillContainerComp(
               filters = FilterType.values().toList(),
               selectedFilter = selected,
               onFilterClick = onFilterClick
        )
        Spacer(modifier = Modifier.height(10.dp))

    }

}
