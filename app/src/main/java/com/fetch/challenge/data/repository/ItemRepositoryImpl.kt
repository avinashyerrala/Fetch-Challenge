package com.fetch.challenge.data.repository

import com.fetch.challenge.data.api.ApiService
import com.fetch.challenge.domain.model.Item
import com.fetch.challenge.domain.repository.ItemRepository
import javax.inject.Inject

class ItemRepositoryImpl @Inject constructor (private val apiService: ApiService) : ItemRepository {

    override suspend fun getItems(): Result<List<Item>> {
        return try {
            val response = apiService.fetchItems()
                .filter { it.name?.isNotBlank() == true }
                .map { Item(it.id, it.listId, it.name!!) }
            Result.success(response)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}