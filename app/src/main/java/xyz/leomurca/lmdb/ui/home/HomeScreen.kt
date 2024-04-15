package xyz.leomurca.lmdb.ui.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.ListItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage

@Composable
fun HomeScreen(viewModel: HomeViewModel = hiltViewModel(), navigateToDetails: (movieId: Long) -> Unit) {
    val state = viewModel.uiState.collectAsState()
    when (val value = state.value) {
        is HomeViewModel.UiState.Loaded -> {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState()),
            ) {
                value.movies.forEach {
                    ListItem(
                        headlineContent = {
                            Text(
                                text = it.title,
                                fontWeight = FontWeight.Bold,
                                fontSize = TextUnit(20F, TextUnitType.Sp),
                            )
                        },
                        supportingContent = {
                            Text(
                                text = it.overview,
                                maxLines = 5,
                                overflow = TextOverflow.Ellipsis,
                                fontSize = TextUnit(12F, TextUnitType.Sp),
                            )
                        },
                        leadingContent = {
                            AsyncImage(
                                model = it.posterImagePath,
                                contentDescription = it.title,
                                modifier = Modifier.height(200.dp),
                            )
                        },
                        modifier = Modifier.clickable {
                            navigateToDetails.invoke(it.id)
                        },
                    )
                    HorizontalDivider()
                }
            }
        }

        is HomeViewModel.UiState.Loading -> {
            Text("Loading...")
        }
    }
}
