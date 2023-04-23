package com.ewake.truepaper.features.feed.presentation

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.selection.toggleable
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.ButtonDefaults.buttonColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import coil.compose.SubcomposeAsyncImage
import com.ewake.truepaper.core.models.domain.NewsModel
import com.ewake.truepaper.core.models.domain.calculateDisTrustPercent
import com.ewake.truepaper.core.models.domain.calculateTrustPercent
import com.ewake.truepaper.features.feed.presentation.viewmodel.FeedViewModel

/**
 * @author Nikolaevskiy Dmitriy
 */

@Composable
fun FeedScreen(
    viewModel: FeedViewModel = hiltViewModel()
) {

    val uiState: FeedViewModel.FeedUiState by viewModel.uiState.collectAsStateWithLifecycle()

    val feedItems = when (uiState) {
        FeedViewModel.FeedUiState.Default -> viewModel.feedFlow.collectAsLazyPagingItems()
        FeedViewModel.FeedUiState.Recommendation -> viewModel.recommendationFeedFlow.collectAsLazyPagingItems()
    }

    feedItems.loadState.let { state ->
        when {
            state.refresh is LoadState.Loading || state.append is LoadState.Loading -> {
                CircularProgressIndicator()
            }
            state.append is LoadState.Error -> {
                Text(text = "Ошибка: ${(state.append as LoadState.Error).error.message}")
            }
        }
    }

    Column(modifier = Modifier.fillMaxSize()) {
        ChipGroup(
            states = listOf(
                FeedViewModel.FeedUiState.Default,
                FeedViewModel.FeedUiState.Recommendation
            ),
            selectedState = uiState,
            onSelectedChanged = viewModel::onStateChanged
        )

        LazyColumn(modifier = Modifier.padding(top = 8.dp, bottom = 8.dp)) {
            items(feedItems) {
                it?.let { model ->
                    NewsCard(
                        model = viewModel.updatedList.firstOrNull { updatedModel ->
                            model.id == updatedModel.id
                        } ?: model,
                        viewModel::onScoreButtonClicked
                    )
                }
            }
        }
    }
}

@Composable
fun NewsCard(
    model: NewsModel,
    onScoreClicked: ((model: NewsModel, value: Boolean) -> Unit)? = null
) {
    Card(
        modifier = Modifier
            .padding(start = 16.dp, end = 16.dp, bottom = 8.dp, top = 8.dp)
            .fillMaxWidth(), elevation = 8.dp,
        shape = RoundedCornerShape(16.dp)
    ) {

        Column {
            model.imageUrl?.let {
                SubcomposeAsyncImage(
                    model = it,
                    contentDescription = null,
                    loading = {
                        CircularProgressIndicator()
                    },
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.height(200.dp)
                )
            }

            Text(
                text = model.newsBody,
                fontSize = 18.sp,
                modifier = Modifier.padding(16.dp)
            )

            Card(
                backgroundColor = Color.DarkGray,
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(start = 16.dp, end = 16.dp, bottom = 8.dp, top = 8.dp)
                    .fillMaxWidth()
            ) {

                if (model.currentUserScore != null) {
                    val text = if (model.trustIndex.trustCount > model.trustIndex.distrustCount) {
                        "Эта новость считается достоверной на ${model.trustIndex.calculateTrustPercent()}% из ${model.trustIndex.totalCount} оценивших"
                    } else {
                        "Эта новость считается недостоверной на ${model.trustIndex.calculateDisTrustPercent()}% из ${model.trustIndex.totalCount} оценивших"
                    }

                    Text(
                        text = text,
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(8.dp),
                        color = Color.White
                    )

                } else {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Text(
                            text = "Вы доверяете этой новости?",
                            color = Color.White,
                            modifier = Modifier.padding(8.dp)
                        )
                        Row(modifier = Modifier.align(Alignment.CenterHorizontally)) {
                            Button(
                                onClick = { onScoreClicked?.invoke(model, true) },
                                modifier = Modifier.padding(8.dp),
                                colors = buttonColors(backgroundColor = Color.Green)
                            ) {
                                Text(text = "Да")
                            }
                            Button(
                                onClick = { onScoreClicked?.invoke(model, false) },
                                modifier = Modifier.padding(8.dp),
                                colors = buttonColors(backgroundColor = Color.Red)
                            ) {
                                Text(text = "Нет")
                            }
                        }
                    }
                }
            }

        }
    }
}

@Composable
fun ChipGroup(
    states: List<FeedViewModel.FeedUiState> = listOf(),
    selectedState: FeedViewModel.FeedUiState? = null,
    onSelectedChanged: (FeedViewModel.FeedUiState) -> Unit = {},
) {
    Column(modifier = Modifier.padding(8.dp)) {
        LazyRow {
            items(states) { item ->
                Chip(
                    name = item.name,
                    isSelected = selectedState == item,
                    onSelectionChanged = {
                        onSelectedChanged(item)
                    },
                )
            }
        }
    }
}

@Composable
fun Chip(
    name: String = "Chip",
    isSelected: Boolean = false,
    onSelectionChanged: (String) -> Unit = {},
) {
    Surface(
        modifier = Modifier.padding(4.dp),
        elevation = 8.dp,
        shape = MaterialTheme.shapes.medium,
        color = if (isSelected) MaterialTheme.colors.primary else Color.LightGray
    ) {
        Row(modifier = Modifier
            .toggleable(
                value = isSelected,
                onValueChange = {
                    onSelectionChanged(name)
                }
            )
        ) {
            Text(
                text = name,
                style = MaterialTheme.typography.body2,
                color = Color.White,
                modifier = Modifier.padding(8.dp)
            )
        }
    }
}