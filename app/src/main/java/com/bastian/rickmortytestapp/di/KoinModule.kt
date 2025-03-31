package com.bastian.rickmortytestapp.di

import com.bastian.rickmortytestapp.data.ApiService
import com.bastian.rickmortytestapp.data.RickMortyRepositoryImpl
import com.bastian.rickmortytestapp.domain.list.GetCharactersUseCase
import com.bastian.rickmortytestapp.domain.RickMortyRepository
import com.bastian.rickmortytestapp.domain.detail.GetLocationUseCase
import com.bastian.rickmortytestapp.domain.detail.GetSingleCharUseCase
import okhttp3.OkHttpClient
import org.koin.dsl.module
import retrofit2.Retrofit
import org.koin.core.module.dsl.viewModelOf
import com.bastian.rickmortytestapp.presentation.list.CharListScreenViewModel
import com.bastian.rickmortytestapp.presentation.detail.DetailScreenViewModel
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.converter.gson.GsonConverterFactory

private const val BASE_URL ="https://rickandmortyapi.com/api/"

val koinmodule = module {

    single {
        OkHttpClient.Builder().addInterceptor(
            HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            }
        ).build()
    }

    single {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(get())
            .build()
    }

    single { get<Retrofit>().create(ApiService::class.java) }

    single<RickMortyRepository> { RickMortyRepositoryImpl(get()) }

    factory { GetCharactersUseCase(get()) }
    factory { GetLocationUseCase(get()) }
    factory { GetSingleCharUseCase(get()) }

    viewModelOf(::CharListScreenViewModel)
    viewModelOf(::DetailScreenViewModel)

}