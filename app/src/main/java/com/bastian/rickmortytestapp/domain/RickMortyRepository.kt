package com.bastian.rickmortytestapp.domain

import androidx.paging.PagingData
import com.bastian.rickmortytestapp.domain.model.CharacterModel
import com.bastian.rickmortytestapp.domain.model.LocationModel
import kotlinx.coroutines.flow.Flow

interface RickMortyRepository {
    fun getChars() : Flow<PagingData<CharacterModel>>

    suspend fun getSingleChar(id: Int) : CharacterModel

    suspend fun getLocation(locationId: Int) : LocationModel
}