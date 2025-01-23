package com.example.sandbox_compose.ui.pages.games

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sandbox_compose.domain.model.GameUIModel
import com.example.sandbox_compose.domain.repository.GamesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GamesViewModel @Inject constructor(
       private val gamesRepository: GamesRepository
) : ViewModel() {

    var gameState by mutableStateOf(GamesState())
        private set

    init {
        getGamesList()
    }

    private fun getGamesList() {
        viewModelScope.launch {
            gameState = gameState.copy(isLoading = true)
            val games = gamesRepository.getListGames()
            gameState = gameState.copy(
                   gamesList = games,
                   isLoading = false
            )
        }
    }
}

data class GamesState(
       val gamesList: List<GameUIModel> = emptyList(),
       val isLoading: Boolean = false
)
