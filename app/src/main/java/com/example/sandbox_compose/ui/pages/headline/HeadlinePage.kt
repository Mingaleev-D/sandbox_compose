package com.example.sandbox_compose.ui.pages.headline

import android.widget.Toast
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LocalFireDepartment
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.sandbox_compose.feature.headline.domain.model.NewsyArticle
import com.example.sandbox_compose.ui.components.HeaderTitle
import com.example.sandbox_compose.ui.components.NewsyArticleItem
import com.example.sandbox_compose.ui.components.PaginationLoadingItem
import com.example.sandbox_compose.ui.theme.itemSpacing

@Composable
fun HeadlinePage(
       headlineViewModel: HeadlineViewModel = hiltViewModel(),
       onItemClick: (Int) -> Unit,
) {
    val headlineState = headlineViewModel.headlineState
    val headlineArticles = headlineState.headlineArticles.collectAsLazyPagingItems()
    HeadlineScreen(
           articles = headlineArticles,
           onItemClick = onItemClick,
           onFavouriteChange = headlineViewModel::onFavouriteChange
    )

}

@Composable
private fun HeadlineScreen(
       articles: LazyPagingItems<NewsyArticle>,
       onItemClick: (Int) -> Unit,
       onFavouriteChange: (NewsyArticle) -> Unit,
) {
    LazyColumn {
        item {
            HeaderTitle(
                   title = "Hot news",
                   icon = Icons.Default.LocalFireDepartment
            )
            Spacer(Modifier.size(itemSpacing))
        }
        items(count = articles.itemCount) { value ->
            articles[value]?.let {
                NewsyArticleItem(
                       article = it,
                       onClick = { clickedItem ->
                           onItemClick(clickedItem.id)
                       },
                       onFavouriteChange = { article ->
                           onFavouriteChange(article as NewsyArticle)
                       }
                )
            }
        }
        item {
            val context = LocalContext.current
            PaginationLoadingItem(
                   pagingState = articles.loadState.mediator?.append,
                   onError = { e ->
                       Toast.makeText(
                              context, e.message,
                              Toast.LENGTH_SHORT
                       ).show()
                   },
                   onLoading = {
                       CircularProgressIndicator(
                              modifier = Modifier.fillParentMaxWidth()
                                  .wrapContentWidth(
                                         align = Alignment.CenterHorizontally
                                  )
                       )
                   }
            )
        }
    }


}
