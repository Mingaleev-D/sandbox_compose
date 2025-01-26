package com.example.sandbox_compose.ui.pages.home

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.example.sandbox_compose.domain.usecase.GetNewsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
       private val getNewsUseCase: GetNewsUseCase
) : ViewModel() {

    var state = mutableStateOf(HomeState())
        private set

    val news =
           getNewsUseCase
               .invoke(listOf("bbc-news", "techcrunch", "bbc-news"))
               .cachedIn(viewModelScope)
}

data class HomeState(
       val newsTicker: String = "",
       val isLoading: Boolean = false,
)
