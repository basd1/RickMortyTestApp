package com.bastian.rickmortytestapp.domain.list

import androidx.paging.PagingData
import com.bastian.rickmortytestapp.domain.RickMortyRepository
import com.bastian.rickmortytestapp.domain.model.CharacterModel
import kotlinx.coroutines.flow.Flow

class GetCharactersUseCase(private val repository: RickMortyRepository) {

    operator fun invoke(): Flow<PagingData<CharacterModel>> {
        return repository.getChars()
    }
}