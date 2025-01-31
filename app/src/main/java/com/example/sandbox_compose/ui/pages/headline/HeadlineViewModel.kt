package com.example.sandbox_compose.ui.pages.headline

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
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
class HeadlineViewModel @Inject constructor(
       private val headlineUseCases: HeadlineUseCases,
) : ViewModel() {

    var headlineState by mutableStateOf(HeadlineState())
        private set

    init {
        initHeadlineArticleData()
    }

    private fun initHeadlineArticleData() {
        val currentCountryCode = Utils.countryCodeList[0].code
        val currentLanguageCode = Utils.languageCodeList[0].code

        headlineState = headlineState.copy(
               headlineArticles = headlineUseCases
                   .fetchHeadlineArticleUseCase(
                          headlineState.selectedHeadlineCategory.category,
                          countryCode = currentCountryCode,
                          languageCode = currentLanguageCode
                   ).cachedIn(viewModelScope)
        )

    }

    fun onFavouriteChange(article: NewsyArticle) {
        viewModelScope.launch {
            val isFavourite = article.favourite
            article.copy(
                   favourite = !isFavourite
            ).also {
                headlineUseCases.updateHeadlineFavouriteUseCase(
                       it
                )
            }
        }
    }

}

data class HeadlineState(
       val headlineArticles: Flow<PagingData<NewsyArticle>> = emptyFlow(),
       val selectedHeadlineCategory: ArticleCategory = ArticleCategory.BUSINESS,
)
