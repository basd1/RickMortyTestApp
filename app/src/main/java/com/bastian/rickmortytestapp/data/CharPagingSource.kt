package com.bastian.rickmortytestapp.data

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.bastian.rickmortytestapp.domain.model.CharacterModel
import com.bastian.rickmortytestapp.domain.model.toDomain

class CharPagingSource(private val apiService: ApiService) : PagingSource<Int, CharacterModel>() {
    override fun getRefreshKey(state: PagingState<Int, CharacterModel>): Int? {
        return state.anchorPosition
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, CharacterModel> {
        return try {
            val page = params.key ?: 1
            val response = apiService.getChars(page)
            Log.d("TESTTEST", "load: $response")
            LoadResult.Page(
                data = response.chars.map { it.toDomain() },
                prevKey = if (page == 1) null else page - 1,
                nextKey = if (response.info.next != null) page + 1 else null
            )

        }catch (e: Exception){LoadResult.Error(e)}
    }
}