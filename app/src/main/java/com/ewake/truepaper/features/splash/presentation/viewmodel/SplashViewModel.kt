package com.ewake.truepaper.features.splash.presentation.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * @author Nikolaevskiy Dmitriy
 */
@HiltViewModel
class SplashViewModel @Inject constructor() : ViewModel() {

    private var _uiState: SplashScreenUIState by mutableStateOf(SplashScreenUIState.Loading)
    val uiState: StateFlow<SplashScreenUIState> = snapshotFlow { _uiState }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(),
        initialValue = _uiState
    )

    fun init() {
        viewModelScope.launch {
            delay(3000)
            _uiState = SplashScreenUIState.Navigating
        }
    }
}

sealed interface SplashScreenUIState {
    object Loading : SplashScreenUIState
    object Navigating : SplashScreenUIState
}