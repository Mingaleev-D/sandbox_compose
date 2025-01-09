package com.example.sandbox_compose.ui.pages.home

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sandbox_compose.data.mapper.toDomainModelList
import com.example.sandbox_compose.data.model.RemoteUnsplashImagesItem
import com.example.sandbox_compose.data.remote.ApiService
import com.example.sandbox_compose.di.AppModule
import com.example.sandbox_compose.domain.model.UnsplashImage
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
       private val apiService: ApiService
) : ViewModel() {

    var images: List<UnsplashImage> by mutableStateOf(emptyList())
        private set

    init {
        getImages()
    }

    private fun getImages() = viewModelScope.launch {
        try {
            val response = apiService.getEditorialListImages()
            // Handle the response (e.g., parse JSON)
            images = response.toDomainModelList() // Update the state with the response (might need parsing)
        } catch (e: Exception) {
            // Handle network errors or other exceptions
            e.printStackTrace()
        }
    }
}
