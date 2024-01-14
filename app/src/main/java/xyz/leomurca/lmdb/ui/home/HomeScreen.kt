package xyz.leomurca.lmdb.ui.home

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState

@Composable
fun HomeScreen(viewModel: HomeViewModel) {
    val state = viewModel.uiState.collectAsState()
    when (val value = state.value) {
        is HomeViewModel.UiState.Loaded -> {
            Column {
                value.movies.forEach {
                    Text(text = it.title)
                }
            }
        }

        is HomeViewModel.UiState.Loading -> {
            Text("Loading...")
        }
    }
}
