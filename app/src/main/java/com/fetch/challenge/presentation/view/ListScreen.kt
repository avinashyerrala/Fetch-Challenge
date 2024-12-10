package com.fetch.challenge.presentation.view

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.fetch.challenge.domain.model.Item

@Composable
fun ListScreen(groupedItems: Map<Int, List<Item>>) {
    LazyColumn(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        groupedItems.forEach { (listId, itemsList) ->
            item {
                Text(
                    text = "List ID: $listId",
                    style = MaterialTheme.typography.displaySmall,
                    modifier = Modifier.padding(vertical = 8.dp)
                )
            }
            items(itemsList) { listItem ->
                Text(
                    text = listItem.name,
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier.padding(vertical = 4.dp)
                )
            }
        }
    }
}
