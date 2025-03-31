package com.bastian.rickmortytestapp.presentation.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.bastian.rickmortytestapp.domain.list.GetCharactersUseCase

class CharListScreenViewModel(
    getCharactersUseCase: GetCharactersUseCase
) : ViewModel() {

    val chars = getCharactersUseCase().cachedIn(viewModelScope)

}