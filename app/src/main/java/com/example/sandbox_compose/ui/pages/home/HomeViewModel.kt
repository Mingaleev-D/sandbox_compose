package com.example.sandbox_compose.ui.pages.home

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.sandbox_compose.feature.discover.domain.usecase.DiscoverUseCases
import com.example.sandbox_compose.feature.headline.domain.model.NewsyArticle
import com.example.sandbox_compose.feature.headline.domain.usecase.HeadlineUseCases
import com.example.sandbox_compose.utils.ArticleCategory
import com.example.sandbox_compose.utils.Utils
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
       private val headlineUseCases: HeadlineUseCases,
       private val discoverUseCases: DiscoverUseCases,
) : ViewModel() {

    var homeState by mutableStateOf(HomeState())
        private set

    init {
        loadArticles()
    }

    private fun loadArticles() {
        homeState = homeState.copy(
               headlineArticles =
               headlineUseCases.fetchHeadlineArticleUseCase(
                      category = homeState.selectedHeadlineCategory.category,
                      countryCode = Utils.countryCodeList[0].code,
                      languageCode = Utils.languageCodeList[0].code
               ).cachedIn(viewModelScope),
               discoverArticles =
               discoverUseCases.fetchDiscoverArticleUseCase(
                      category = homeState.selectedDiscoverCategory.category,
                      language = "en",
                      country = "us"
               ).cachedIn(viewModelScope)
        )
    }

    fun onHomeUIEvents(homeUIEvents: HomeUIEvents) {
        when (homeUIEvents) {
            HomeUIEvents.ViewMoreClicked -> {}
            is HomeUIEvents.ArticleClicked -> {}
            is HomeUIEvents.CategoryChange -> {}
            is HomeUIEvents.PreferencePanelToggle -> {}
            is HomeUIEvents.OnHeadLineFavouriteChange -> {
                viewModelScope.launch {
                    val isFavourite = homeUIEvents.article.favourite
                    val update = homeUIEvents.article.copy(
                           favourite = !isFavourite
                    )
                    headlineUseCases.updateHeadlineFavouriteUseCase(
                           update
                    )
                }
            }

            is HomeUIEvents.OnDiscoverFavouriteChange -> {

            }
        }
    }

    private fun updateCategory(homeUIEvents: HomeUIEvents.CategoryChange) {
        homeState = homeState.copy(
               selectedDiscoverCategory = homeUIEvents.category
        )
        // TODO:1 Remove category update since this is directly updated when saving
        viewModelScope.launch {
            discoverUseCases.updateCurrentCategoryUseCase(
                   homeState.selectedDiscoverCategory.category
            )
        }
    }

    private fun updateDiscoverArticles() {
        val data = discoverUseCases.fetchDiscoverArticleUseCase(
               category = homeState.selectedDiscoverCategory.category,
               language = "en",
               country = "us"
        ).cachedIn(viewModelScope)
        homeState = homeState.copy(
               discoverArticles = data
        )

    }
}

sealed class HomeUIEvents {
    object ViewMoreClicked:HomeUIEvents()
    data class ArticleClicked(val url:String):HomeUIEvents()
    data class CategoryChange(val category: ArticleCategory) : HomeUIEvents()
    data class PreferencePanelToggle(val isOpen: Boolean) : HomeUIEvents()
    data class OnHeadLineFavouriteChange(val article: NewsyArticle) : HomeUIEvents()
    data class OnDiscoverFavouriteChange(val article: NewsyArticle) : HomeUIEvents()
}

data class HomeState(
       val headlineArticles: Flow<PagingData<NewsyArticle>> = emptyFlow(),
       val discoverArticles: Flow<PagingData<NewsyArticle>> = emptyFlow(),
       val selectedDiscoverCategory: ArticleCategory = ArticleCategory.SPORTS,
       val selectedHeadlineCategory: ArticleCategory = ArticleCategory.BUSINESS,
)
