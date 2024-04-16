package xyz.leomurca.lmdb.ui.details

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
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
fun DetailsScreen(viewModel: DetailsViewModel = hiltViewModel()) {
    val state = viewModel.uiState.collectAsState()
    when (val value = state.value) {
        is DetailsViewModel.UiState.Loaded -> {
            Column {
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
                            .drawWithContent {
                                drawContent()
                                drawRect(
                                    brush = Brush.verticalGradient(gradientBlackToTransparent),
                                    blendMode = BlendMode.DstIn,
                                )
                            },
                    )

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
                        text = value.details.overview,
                        fontSize = TextUnit(12F, TextUnitType.Sp),
                    )
                }
            }
        }

        is DetailsViewModel.UiState.Loading -> {
            Text(text = "Loading...")
        }
    }
}
