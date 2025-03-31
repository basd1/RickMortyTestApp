package com.bastian.rickmortytestapp.presentation.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bastian.rickmortytestapp.domain.detail.GetLocationUseCase
import com.bastian.rickmortytestapp.domain.detail.GetSingleCharUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class DetailScreenViewModel(private val getSingleCharUseCase: GetSingleCharUseCase,
    private val getLocationUseCase: GetLocationUseCase) : ViewModel() {

    private val _uiState = MutableStateFlow<DetailUiState>(DetailUiState.Loading)
    val uiState: StateFlow<DetailUiState> = _uiState.asStateFlow()

        fun loadChar(id: Int){
            viewModelScope.launch {
                try {
                    val char = getSingleCharUseCase.invoke(id)

                    val locationId = char.location.url.substringAfterLast("/").toIntOrNull()
                    val location = locationId?.let { getLocationUseCase(it) }

                    _uiState.value = DetailUiState.Success(char, location)


                }catch (e: Exception){
                    _uiState.value = DetailUiState.Error("Ha ocurrido un error cargando el Personaje")
                }
            }
        }
}