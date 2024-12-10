package com.fetch.challenge.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fetch.challenge.domain.model.Item
import com.fetch.challenge.domain.usecase.GetItemsUseCase
import com.fetch.challenge.presentation.utils.UIState
import kotlinx.coroutines.launch
import javax.inject.Inject

class MainViewModel @Inject constructor(private val getItemsUseCase: GetItemsUseCase) : ViewModel() {

    private val _uiState = MutableLiveData<UIState<Map<Int, List<Item>>>>(UIState.Loading)
    val uiState: LiveData<UIState<Map<Int, List<Item>>>> get() = _uiState

    init {
        fetchItems()
    }
    private fun fetchItems() {
        _uiState.value = UIState.Loading
        viewModelScope.launch {
            val result = getItemsUseCase()
            _uiState.value = when {
                result.isSuccess -> UIState.Success(result.getOrThrow())
                result.isFailure -> UIState.Error(result.exceptionOrNull()?.localizedMessage ?: "An error occurred")
                else -> UIState.Error("Unknown state")
            }
        }
    }
}
