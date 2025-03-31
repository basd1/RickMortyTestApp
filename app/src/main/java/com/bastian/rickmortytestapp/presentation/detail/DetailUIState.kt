package com.bastian.rickmortytestapp.presentation.detail

import com.bastian.rickmortytestapp.domain.model.CharacterModel
import com.bastian.rickmortytestapp.domain.model.LocationModel

sealed class DetailUiState {
    data object Loading : DetailUiState()
    data class Success(val character: CharacterModel, val location: LocationModel?) : DetailUiState()
    data class Error(val message: String) : DetailUiState()
}