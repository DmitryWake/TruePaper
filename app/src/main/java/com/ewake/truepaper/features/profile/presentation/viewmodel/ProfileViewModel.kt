package com.ewake.truepaper.features.profile.presentation.viewmodel

import android.content.Context
import androidx.annotation.StringRes
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ewake.truepaper.R
import com.ewake.truepaper.core.domain.repository.UserRepository
import com.ewake.truepaper.features.splash.presentation.viewmodel.SplashScreenUIState
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import java.util.*
import javax.inject.Inject

/**
 * @author Nikolaevskiy Dmitriy
 */
@HiltViewModel
class ProfileViewModel @Inject constructor(
    @ApplicationContext
    private val context: Context,
    private val userRepository: UserRepository,
) : ViewModel() {

    private var _uiState: ProfileScreenUiState by mutableStateOf(ProfileScreenUiState.Loading)
    val uiState: StateFlow<ProfileScreenUiState> = snapshotFlow { _uiState }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(),
        initialValue = _uiState
    )

    fun init() {
        viewModelScope.launch {
            _uiState = ProfileScreenUiState.Loading
            runCatching { userRepository.getCurrentUser() }
                .onSuccess {
                    _uiState =
                        it?.let { ProfileScreenUiState.Profile(it.id, Date(it.registerDate)) }
                            ?: ProfileScreenUiState.Error(context.getString(R.string.profile_null_error))
                }
                .onFailure {
                    _uiState = ProfileScreenUiState.Error(it.message.orEmpty())
                }
        }
    }

    sealed interface ProfileScreenUiState {
        object Loading : ProfileScreenUiState
        data class Error(val message: String) : ProfileScreenUiState
        data class Profile(
            val userId: String,
            val registerDate: Date
        ) : ProfileScreenUiState
    }
}