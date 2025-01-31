package com.example.sandbox_compose.ui.pages.detail

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.unit.Constraints
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sandbox_compose.feature.details.domain.model.DetailArticle
import com.example.sandbox_compose.feature.details.domain.usecase.DetailUseCases
import com.example.sandbox_compose.ui.navigation.Routes
import com.example.sandbox_compose.utils.Constants
import com.example.sandbox_compose.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
       private val detailUseCases: DetailUseCases,
       savedStateHandle: SavedStateHandle,
) : ViewModel() {

    private val unknownRoute = "unknown"
    val id: Int = savedStateHandle.get<Int>(Constants.articleId) ?: -1
    private val route: String = savedStateHandle.get<String>(Constants.screenType) ?: unknownRoute
    var detailState by mutableStateOf(DetailState())
        private set

    init {
        load()
    }

    private fun load() {
        viewModelScope.launch {
            when (route) {
                Routes.DiscoverPage().route -> {
                    detailState = detailState.copy(
                           selectedDetailArticle = detailUseCases.getDetailDiscoverArticleUseCase(
                                  id
                           ),
                           error = false
                    )
                }

                Routes.HeadlinesPage().route -> {
                    detailState = detailState.copy(
                           selectedDetailArticle = detailUseCases.getDetailHeadlineArticleUseCase(
                                  id
                           ),
                           error = false
                    )
                }
                //                Routes.SearchPage().route -> {
                //                    detailState = detailState.copy(
                //                           selectedDetailArticle = detailUseCases.getDetailSearchArticleUseCase(
                //                                  id
                //                           ),
                //                           error = false
                //                    )
                //                }
                unknownRoute -> {
                    detailState = detailState.copy(
                           error = true
                    )
                }
            }
        }
    }

}

data class DetailState(
       val selectedDetailArticle: Flow<Resource<DetailArticle>> = emptyFlow(),
       val error: Boolean = false,
)
