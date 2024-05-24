package xyz.leomurca.lmdb.ui.details

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.rounded.PlayArrow
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonColors
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.CompositingStrategy
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import xyz.leomurca.lmdb.ui.theme.gradientBlackToTransparent

@Composable
fun DetailsScreen(viewModel: DetailsViewModel = hiltViewModel(), onTapBack: () -> Unit) {
    val state = viewModel.uiState.collectAsState()
    when (val value = state.value) {
        is DetailsViewModel.UiState.Loaded -> {
            Scaffold(
                floatingActionButton = {
                    ExtendedFloatingActionButton(
                        text = { Text(text = "Play trailer", color = Color.White) },
                        icon = {
                            Icon(
                                imageVector = Icons.Rounded.PlayArrow,
                                contentDescription = "Play trailer",
                                tint = Color.White,
                            )
                        },
                        onClick = { },
                        containerColor = Color.Black
                    )
                }
            ) { paddingValues ->
                Column(Modifier
                    .padding(paddingValues)
                    .verticalScroll(rememberScrollState())) {
                    Box(
                        Modifier
                            .fillMaxWidth()
                            .height(350.dp),
                    ) {
                        AsyncImage(
                            model = value.details.backdropImagePath,
                            contentDescription = value.details.title,
                            modifier = Modifier
                                .fillMaxWidth()
                                .graphicsLayer(compositingStrategy = CompositingStrategy.Offscreen)
                                .background(Color.Black)
                                .drawWithContent {
                                    drawContent()
                                    drawRect(
                                        brush = Brush.verticalGradient(gradientBlackToTransparent),
                                        blendMode = BlendMode.DstIn,
                                    )
                                },
                        )

                        IconButton(
                            onClick = { onTapBack.invoke() },
                            modifier = Modifier.size(50.dp).padding(top = 15.dp)
                        ) {
                            Icon(
                                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                                contentDescription = "Arrow Back",
                                tint = Color.Black
                            )
                        }

                        Row(Modifier.offset(10.dp, 170.dp)) {
                            AsyncImage(
                                model = value.details.posterImagePath,
                                contentDescription = value.details.title,
                                modifier = Modifier
                                    .height(150.dp)
                                    .border(BorderStroke(2.dp, Color.Black)),
                            )
                            Column(modifier = Modifier.padding(vertical = 30.dp, horizontal = 10.dp)) {
                                Text(
                                    text = value.details.title,
                                    maxLines = 2,
                                    overflow = TextOverflow.Ellipsis,
                                    fontWeight = FontWeight.SemiBold,
                                    fontSize = TextUnit(20F, TextUnitType.Sp),
                                )
                                Text(
                                    text = value.details.releaseDate,
                                    fontSize = TextUnit(12F, TextUnitType.Sp),
                                )
                            }
                        }
                    }
                    Column(Modifier.padding(horizontal = 10.dp)) {
                        Text(
                            text = "Overview",
                            fontWeight = FontWeight.SemiBold,
                            fontSize = TextUnit(16F, TextUnitType.Sp),
                        )
                        Text(
                            text = value.details.overview,
                            fontSize = TextUnit(12F, TextUnitType.Sp),
                        )
                        Text(
                            text = "Budget",
                            fontWeight = FontWeight.SemiBold,
                            fontSize = TextUnit(16F, TextUnitType.Sp),
                            modifier = Modifier.padding(top = 10.dp)
                        )
                        Text(
                            text = value.details.budget,
                            fontSize = TextUnit(12F, TextUnitType.Sp),
                        )

                        Text(
                            text = "Revenue",
                            fontWeight = FontWeight.SemiBold,
                            fontSize = TextUnit(16F, TextUnitType.Sp),
                            modifier = Modifier.padding(top = 10.dp)
                        )
                        Text(
                            text = value.details.revenue,
                            fontSize = TextUnit(12F, TextUnitType.Sp),
                        )
                    }
                }
            }
        }

        is DetailsViewModel.UiState.Loading -> {
            Text(text = "Loading...")
        }
    }
}
