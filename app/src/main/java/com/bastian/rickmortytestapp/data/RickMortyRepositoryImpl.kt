package com.bastian.rickmortytestapp.data

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.bastian.rickmortytestapp.domain.RickMortyRepository
import com.bastian.rickmortytestapp.domain.model.CharacterModel
import com.bastian.rickmortytestapp.domain.model.LocationModel
import com.bastian.rickmortytestapp.domain.model.toDomain
import kotlinx.coroutines.flow.Flow

class RickMortyRepositoryImpl(private val apiService: ApiService) : RickMortyRepository {

    companion object {
        const val MAX_ITEMS = 20
        const val PREFETCH_ITEMS = 3
    }

    override fun getChars(): Flow<PagingData<CharacterModel>> {
        return Pager(
            config = PagingConfig(pageSize = MAX_ITEMS, prefetchDistance = PREFETCH_ITEMS),
            pagingSourceFactory = {
                CharPagingSource(apiService)
            }
        ).flow
    }

    override suspend fun getSingleChar(id: Int): CharacterModel {
        return apiService.getSingleChar(id).toDomain()
    }

    override suspend fun getLocation(locationId: Int): LocationModel {
        return apiService.getLocation(locationId).toDomain()
    }
}