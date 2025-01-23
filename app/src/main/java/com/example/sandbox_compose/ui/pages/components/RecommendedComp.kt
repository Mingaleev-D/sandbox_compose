package com.example.sandbox_compose.ui.pages.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.sandbox_compose.domain.model.GameUIModel

@Composable
fun RecommendedComp(
       modifier: Modifier = Modifier,
       selected: FilterType,
       gameList: List<GameUIModel>,
       onFilterClick: (FilterType) -> Unit,
       onGamesClick: (GameUIModel) -> Unit
) {
    Column(
           modifier = modifier.fillMaxWidth(),
    ) {
        //  item {
        CategoryTitleComp(
               txt = "Recommended For You"
        )
        Spacer(modifier = Modifier.height(10.dp),)
        //  }
        // item {
        FilterPillContainerComp(
               filters = FilterType.values().toList(),
               selectedFilter = selected,
               onFilterClick = onFilterClick
        )
        // }
    }

}
