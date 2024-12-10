package com.fetch.challenge.domain.usecase

import com.fetch.challenge.domain.model.Item
import com.fetch.challenge.domain.repository.ItemRepository
import javax.inject.Inject

class GetItemsUseCaseImpl @Inject constructor(private val repository: ItemRepository) : GetItemsUseCase {

    override suspend fun invoke(): Result<Map<Int, List<Item>>> {

         return repository.getItems().mapCatching { result ->
             result.sortedWith(compareBy({ it.listId }, { it.name }))
                .groupBy { it.listId }
        }
    }
}
