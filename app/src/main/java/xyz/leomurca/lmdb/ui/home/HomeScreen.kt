package xyz.leomurca.lmdb.ui.home

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.ListItem
import androidx.compose.material3.SearchBar
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import xyz.leomurca.lmdb.ui.extensions.shimmerEffect

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(viewModel: HomeViewModel = hiltViewModel(), onTapMovie: (movieId: Long) -> Unit) {
    val state = viewModel.uiState.collectAsState()
    val searchText = viewModel.searchText.collectAsState()

    when (val value = state.value) {
        is HomeViewModel.UiState.Loaded -> {
            when (value) {
                is HomeViewModel.UiState.Loaded.Success -> {
                    Column {
                        SearchBar(
                            query = searchText.value,
                            onQueryChange = viewModel::onSearchTextChange,
                            onSearch = { },
                            active = false,
                            onActiveChange = { },
                            placeholder = { Text(text = "Search your movie") },
                            leadingIcon = {
                                Icon(
                                    imageVector = Icons.Default.Search,
                                    contentDescription = "Search",
                                    tint = Color.Black
                                )
                            },
                            modifier = Modifier.fillMaxWidth(),
                            content = {}
                        )
                        LazyColumn(
                            modifier = Modifier
                                .fillMaxSize()
                        ) {
                            items(value.movies) { movie ->
                                ListItem(
                                    headlineContent = {
                                        Text(
                                            text = movie.title,
                                            fontWeight = FontWeight.Bold,
                                            fontSize = TextUnit(20F, TextUnitType.Sp),
                                        )
                                    },
                                    supportingContent = {
                                        Text(
                                            text = movie.overview,
                                            maxLines = 5,
                                            overflow = TextOverflow.Ellipsis,
                                            fontSize = TextUnit(12F, TextUnitType.Sp),
                                        )
                                    },
                                    leadingContent = {
                                        if (movie.posterImagePath.isNotBlank()) {
                                            AsyncImage(
                                                model = movie.posterImagePath,
                                                contentDescription = movie.title,
                                                modifier = Modifier.height(200.dp),
                                            )
                                        } else {
                                            PosterImageFallback()
                                        }
                                    },
                                    modifier = Modifier.clickable {
                                        onTapMovie.invoke(movie.id)
                                    },
                                )
                                HorizontalDivider()
                            }
                        }
                    }

                }

                is HomeViewModel.UiState.Loaded.Error -> Text(text = value.message)
            }
        }

        is HomeViewModel.UiState.Loading -> {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(10.dp)
            ) {
                items(10) {
                    LoadingPlaceholder()
                }
            }
        }
    }
}

@Composable
private fun PosterImageFallback() {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .height(200.dp)
            .width(135.dp)
            .background(Color.Gray)
    ) {
        Text(text = "Image not found", color = Color.White)
    }
}

@Composable
private fun LoadingPlaceholder() {
    Row(Modifier.padding(top = 10.dp)) {
        Box(
            modifier = Modifier
                .width(135.dp)
                .height(200.dp)
                .shimmerEffect()
        )
        Spacer(modifier = Modifier.width(12.dp))
        Column(modifier = Modifier.weight(1f)) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(30.dp)
                    .shimmerEffect()
            )
            Spacer(modifier = Modifier.height(16.dp))
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(10.dp)
                    .shimmerEffect()
            )
            Spacer(modifier = Modifier.height(4.dp))
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(10.dp)
                    .shimmerEffect()
            )
            Spacer(modifier = Modifier.height(4.dp))
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(10.dp)
                    .shimmerEffect()
            )
            Spacer(modifier = Modifier.height(4.dp))
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(10.dp)
                    .shimmerEffect()
            )
        }
    }
}
