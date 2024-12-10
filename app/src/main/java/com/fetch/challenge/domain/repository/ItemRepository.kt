package com.fetch.challenge.domain.repository

import com.fetch.challenge.domain.model.Item

interface ItemRepository {
    suspend fun getItems(): Result<List<Item>>
}