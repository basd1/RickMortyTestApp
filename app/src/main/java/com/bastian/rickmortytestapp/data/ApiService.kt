package com.bastian.rickmortytestapp.data

import com.bastian.rickmortytestapp.data.model.CharacterDto
import com.bastian.rickmortytestapp.data.model.LocationDto
import com.bastian.rickmortytestapp.data.model.ResponseDto
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @GET("character")
    suspend fun getChars(@Query("page") page: Int) : ResponseDto

    @GET("character/{id}")
    suspend fun getSingleChar(@Path("id") id: Int) : CharacterDto

    @GET("location/{id}")
    suspend fun getLocation(@Path("id") locationID: Int) : LocationDto
}