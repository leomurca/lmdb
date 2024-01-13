package xyz.leomurca.lmdb.ui.home

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@Composable
fun HomeScreen(viewModel: HomeViewModel) {
    Text(viewModel.text)
}

@HiltViewModel
class HomeViewModel @Inject constructor() : ViewModel() {
    val text = "Hello hilt!"
}
