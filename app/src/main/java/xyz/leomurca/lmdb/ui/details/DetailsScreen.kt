package xyz.leomurca.lmdb.ui.details

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage

@Composable
fun DetailsScreen(viewModel: DetailsViewModel = hiltViewModel()) {
    val state = viewModel.uiState.collectAsState()
    when (val value = state.value) {
        is DetailsViewModel.UiState.Loaded -> {
            Column {
                AsyncImage(
                    model = value.details.backdropImagePath,
                    contentDescription = value.details.title,
                    modifier = Modifier.height(200.dp),
                )
                Text(value.details.title)
            }
        }

        is DetailsViewModel.UiState.Loading -> {
            Text(text = "Loading...")
        }
    }
}
