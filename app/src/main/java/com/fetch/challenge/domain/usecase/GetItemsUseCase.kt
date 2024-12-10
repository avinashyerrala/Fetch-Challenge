package com.fetch.challenge.domain.usecase

import com.fetch.challenge.domain.model.Item

interface GetItemsUseCase {
    suspend operator fun invoke(): Result<Map<Int, List<Item>>>
}