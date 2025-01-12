package com.example.sandbox_compose.domain.repository

import com.example.sandbox_compose.domain.model.NetworkStatus
import kotlinx.coroutines.flow.StateFlow

interface NetworkConnectivityObserver {
    val networkStatus: StateFlow<NetworkStatus>
}
