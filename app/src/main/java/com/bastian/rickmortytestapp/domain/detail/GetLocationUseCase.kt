package com.bastian.rickmortytestapp.domain.detail

import com.bastian.rickmortytestapp.domain.RickMortyRepository
import com.bastian.rickmortytestapp.domain.model.LocationModel

class GetLocationUseCase(private val repository: RickMortyRepository) {
    suspend operator fun invoke(id: Int) : LocationModel {
        return repository.getLocation(id)
    }
}