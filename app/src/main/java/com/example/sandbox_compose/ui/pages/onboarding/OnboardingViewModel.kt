package com.example.sandbox_compose.ui.pages.onboarding

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sandbox_compose.domain.usecase.ReadAppEntryUseCase
import com.example.sandbox_compose.domain.usecase.SaveAppEntryUseCase
import com.example.sandbox_compose.ui.navigation.Routes
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class OnboardingViewModel @Inject constructor(
       private val saveAppEntryUseCase: SaveAppEntryUseCase,
       private val readAppEntryUseCase: ReadAppEntryUseCase
) : ViewModel() {

    private val _startDestination = mutableStateOf(Routes.AppStartNavigation.routes)
    val startDestination: State<String> = _startDestination

    init {
        viewModelScope.launch {
            readAppEntryUseCase().onEach { shouldStartFromHomeScreen ->
                if (shouldStartFromHomeScreen) {
                    _startDestination.value = Routes.NewsNavigation.routes
                } else {
                    _startDestination.value = Routes.AppStartNavigation.routes
                }
            }.launchIn(viewModelScope)
        }

    }

    fun onEvent(event: OnboardingEvent) {
        when (event) {
            OnboardingEvent.SaveAppEntry -> {
                viewModelScope.launch {
                    saveAppEntryUseCase()
                }

            }
        }
    }
}

sealed class OnboardingEvent {
    data object SaveAppEntry : OnboardingEvent()
}
