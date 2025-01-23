package com.example.sandbox_compose.ui.pages.games

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sandbox_compose.domain.model.GameUIModel
import com.example.sandbox_compose.domain.repository.GamesRepository
import com.example.sandbox_compose.ui.pages.components.FilterType
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import kotlinx.coroutines.supervisorScope
import javax.inject.Inject

@HiltViewModel
class GamesViewModel @Inject constructor(
       private val gamesRepository: GamesRepository
) : ViewModel() {

    var gameState by mutableStateOf(GamesState())
        private set

    init {
        gameState = gameState.copy(isLoading = true)
        viewModelScope.launch {
            supervisorScope {
                val gamesList = launch { getGamesList() }
                val upComingGames = launch { getUpcomingGames() }
                listOf(gamesList, upComingGames).forEach { it.join() }
                gameState = gameState.copy(isLoading = false)
            }
        }

    }

    private suspend fun getGamesList() {
        gamesRepository.getListGames().onSuccess { data ->
            gameState = gameState.copy(
                   gamesList = data,
            )
        }.onFailure {
            println()
        }
    }

    private suspend fun getUpcomingGames() {
        gamesRepository.getUpcomingGames().onSuccess { data ->
            gameState = gameState.copy(
                   upComingGames = data,
            )
        }.onFailure {
            println()
        }
    }

    fun onEvent(event: GameEvent) {
        when(event) {
            is GameEvent.ChangeFilter -> {
                if(event.filter != gameState.selectedFilter) {
                    gameState = gameState.copy(
                           selectedFilter = event.filter
                    )
                }

            }
            is GameEvent.OnFameClick -> TODO()
        }
    }
}

data class GamesState(
       val gamesList: List<GameUIModel> = emptyList(),
       val upComingGames: List<GameUIModel> = emptyList(),
       val isLoading: Boolean = false,
       val selectedFilter: FilterType = FilterType.POLARITY,
       val filterGames: List<GameUIModel> = emptyList()
)

sealed class GameEvent {
    data class ChangeFilter(val filter: FilterType) : GameEvent()
    data class OnFameClick(val gameId: Int) : GameEvent()
}
