package com.example.sandbox_compose.ui.pages

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sandbox_compose.core.data.mapper.EpisodeDto
import com.example.sandbox_compose.core.data.repository.CharacterRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AllEpisodesViewModel @Inject constructor(
       private val repository: CharacterRepo
) : ViewModel() {

    private val _uiState = MutableStateFlow<AllEpisodesUiState>(AllEpisodesUiState.Loading)
    val uiState = _uiState.asStateFlow()

    fun refreshAllEpisodes(forceRefresh: Boolean = false) = viewModelScope.launch {
        if (forceRefresh) _uiState.update { AllEpisodesUiState.Loading }
        repository.fetchAllEpisodes()
               .onSuccess { episodeList ->
                   _uiState.update {
                       AllEpisodesUiState.Success(
                              data = episodeList.groupBy {
                                  it.seasonNumber.toString()
                              }.mapKeys {
                                  "Season ${it.key}"
                              }
                       )
                   }
               }.onFailure {
                   _uiState.update { AllEpisodesUiState.Error }
               }
    }
}

sealed interface AllEpisodesUiState {
    object Error : AllEpisodesUiState
    object Loading : AllEpisodesUiState
    data class Success(val data: Map<String, List<EpisodeDto>>) : AllEpisodesUiState
}
