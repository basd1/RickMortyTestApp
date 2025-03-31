package com.bastian.rickmortytestapp.domain.detail

import com.bastian.rickmortytestapp.domain.RickMortyRepository
import com.bastian.rickmortytestapp.domain.model.CharacterModel

class GetSingleCharUseCase(private val repository: RickMortyRepository) {

    suspend operator fun invoke(id: Int) : CharacterModel {
        return repository.getSingleChar(id)
    }
}