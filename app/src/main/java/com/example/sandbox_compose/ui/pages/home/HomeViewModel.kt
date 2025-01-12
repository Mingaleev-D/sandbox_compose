package com.example.sandbox_compose.ui.pages.home

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sandbox_compose.domain.model.UnsplashImage
import com.example.sandbox_compose.domain.repository.ImageRepository
import com.example.sandbox_compose.utils.SnackbarEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import java.net.UnknownHostException
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
       private val repository: ImageRepository
) : ViewModel() {

    var images: List<UnsplashImage> by mutableStateOf(emptyList())
        private set

    private val _snackbarEvent = Channel<SnackbarEvent>()
    val snackbarEvent = _snackbarEvent.receiveAsFlow()

    init {
        getImages()
    }

    private fun getImages() {
        viewModelScope.launch {
            try {
                val result = repository.getEditorialFeedImages()
                images = result
            } catch (e: UnknownHostException) {
                _snackbarEvent.send(
                       SnackbarEvent(message = "No Internet connection. Please check you network.")
                )
            } catch (e: Exception) {
                _snackbarEvent.send(
                       SnackbarEvent(message = "Something went wrong: ${e.message}")
                )
            }
        }
    }
}
