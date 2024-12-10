package com.fetch.challenge.data.api

import com.fetch.challenge.data.model.ItemResponse
import retrofit2.http.GET

interface ApiService {
    @GET("hiring.json")
    suspend fun fetchItems(): List<ItemResponse>
}