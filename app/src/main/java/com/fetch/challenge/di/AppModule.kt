package com.fetch.challenge.di

import com.fetch.challenge.data.api.ApiService
import com.fetch.challenge.data.repository.ItemRepositoryImpl
import com.fetch.challenge.domain.repository.ItemRepository
import com.fetch.challenge.domain.usecase.GetItemsUseCase
import com.fetch.challenge.domain.usecase.GetItemsUseCaseImpl
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class AppModule {

    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient = OkHttpClient.Builder().build()

    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit = Retrofit.Builder()
        .baseUrl("https://fetch-hiring.s3.amazonaws.com/")
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    @Provides
    @Singleton
    fun provideApiService(retrofit: Retrofit): ApiService = retrofit.create(ApiService::class.java)

    @Provides
    @Singleton
    fun provideItemRepository(apiService: ApiService): ItemRepository = ItemRepositoryImpl(apiService)

    @Provides
    @Singleton
    fun provideGetItemsUseCase(repository: ItemRepository): GetItemsUseCase = GetItemsUseCaseImpl(repository)
}