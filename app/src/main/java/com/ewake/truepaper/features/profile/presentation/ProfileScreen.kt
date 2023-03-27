package com.ewake.truepaper.features.profile.presentation

import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.ewake.truepaper.features.profile.presentation.viewmodel.ProfileViewModel

/**
 * @author Nikolaevskiy Dmitriy
 */

@Composable
fun ProfileScreen(viewModel: ProfileViewModel = hiltViewModel()) {
    val uiState: ProfileViewModel.ProfileScreenUiState by viewModel.uiState.collectAsStateWithLifecycle()

    viewModel.init()

    when (uiState) {
        is ProfileViewModel.ProfileScreenUiState.Error -> {
            Text(text = (uiState as ProfileViewModel.ProfileScreenUiState.Error).message)
        }
        ProfileViewModel.ProfileScreenUiState.Loading -> CircularProgressIndicator()
        is ProfileViewModel.ProfileScreenUiState.Profile -> {
            val profile = uiState as ProfileViewModel.ProfileScreenUiState.Profile
            Text(text = "Ваш айди: ${profile.userId}\nДата регистрации: ${profile.registerDate}")
        }
    }
}