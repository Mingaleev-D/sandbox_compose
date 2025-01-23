package com.example.sandbox_compose.ui.pages.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun FilterPillContainerComp(
       modifier: Modifier = Modifier,
       filters: List<FilterType>,
       selectedFilter: FilterType,
       onFilterClick: (FilterType) -> Unit
) {
    LazyRow(
           modifier = modifier.fillMaxWidth().padding(start = 16.dp),
           horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(filters) {
            FilterPillComp(typeFil = it, isSelected = selectedFilter == it) {
                onFilterClick(it)
            }
        }
    }

}

@Preview(showBackground = true)
@Composable
private fun FilterPillContainerCompPreview() {
    FilterPillContainerComp(
           filters = FilterType.values().toList(),
           selectedFilter = FilterType.POLARITY,
           onFilterClick = {}
    )
}
