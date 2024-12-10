package com.fetch.challenge.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.lifecycle.ViewModelProvider
import com.fetch.challenge.domain.model.Item
import com.fetch.challenge.presentation.utils.UIState
import com.fetch.challenge.presentation.view.ErrorScreen
import com.fetch.challenge.presentation.view.ListScreen
import com.fetch.challenge.presentation.view.LoadingScreen
import com.fetch.challenge.presentation.viewmodel.MainViewModel
import javax.inject.Inject

class MainActivity : ComponentActivity() {
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private lateinit var viewModel: MainViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        (application as FetchApplication).appComponent.inject(this)
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this, viewModelFactory)[MainViewModel::class]
        setContent {
            MaterialTheme {
                AppContent(viewModel)
            }
        }
    }
}

@Composable
fun AppContent(viewModel: MainViewModel) {
    val uiState by viewModel.uiState.observeAsState(UIState.Loading)

    when (uiState) {
        is UIState.Loading -> LoadingScreen()
        is UIState.Success -> ListScreen((uiState as UIState.Success<Map<Int, List<Item>>>).data)
        is UIState.Error -> ErrorScreen((uiState as UIState.Error).message)
    }
}