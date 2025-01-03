package com.example.sandbox_compose.ui.pages

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sandbox_compose.core.data.mapper.CharacterDto
import com.example.sandbox_compose.core.data.repository.CharacterRepo
import com.example.sandbox_compose.ui.components.DataPoint
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CharacterDetailsViewModel @Inject constructor(
       private val characterRepo: CharacterRepo
) : ViewModel() {

    private val _internalStorageFlow =
           MutableStateFlow<CharacterDetailsState>(CharacterDetailsState.Loading)
    val internalStorageFlow: StateFlow<CharacterDetailsState> = _internalStorageFlow.asStateFlow()

    fun fetchCharacterDetails(characterId: Int) = viewModelScope.launch {
        _internalStorageFlow.value = CharacterDetailsState.Loading

        characterRepo.fetchCharacter(characterId)
               .onSuccess { characterDto ->
                   val dataPoints = buildList {
                       characterDto.let { character ->
                           add(DataPoint("Last known location", character.location.name))
                           add(DataPoint("Species", character.species))
                           add(DataPoint("Gender", character.gender.displayName))
                           character.type.takeIf { it.isNotEmpty() }?.let { type ->
                               add(DataPoint("Type", type))
                           }
                           add(DataPoint("Origin", character.origin.name))
                           add(DataPoint("Episode count", character.episodeIds.size.toString()))
                       }
                   }
                   _internalStorageFlow.value = CharacterDetailsState.Success(
                          characterDto = characterDto,
                          characterDataPoints = dataPoints
                   )
               }
               .onFailure {
                   _internalStorageFlow.value = CharacterDetailsState.Error(it.message.toString())
               }
    }
}

sealed interface CharacterDetailsState {
    object Loading : CharacterDetailsState
    data class Success(
           val characterDto: CharacterDto,
           val characterDataPoints: List<DataPoint>
    ) : CharacterDetailsState

    data class Error(val message: String) : CharacterDetailsState
}
