package com.ewake.truepaper.features.feed.presentation.viewmodel

import androidx.compose.runtime.*
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.ewake.truepaper.core.models.domain.NewsModel
import com.ewake.truepaper.features.feed.domain.di.FeedRepositoryQualifier
import com.ewake.truepaper.features.feed.domain.di.RecommendationFeedRepositoryQualifier
import com.ewake.truepaper.features.feed.domain.repository.FeedRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * @author Nikolaevskiy Dmitriy
 */
@HiltViewModel
class FeedViewModel @Inject constructor(
    @FeedRepositoryQualifier
    private val feedRepository: FeedRepository,
    @RecommendationFeedRepositoryQualifier
    private val recommendationFeedRepository: FeedRepository,
) : ViewModel() {

    val feedFlow = feedRepository.getPagingFeedFlow(FEED_PAGE_SIZE).cachedIn(viewModelScope)
    val recommendationFeedFlow =
        recommendationFeedRepository.getPagingFeedFlow(FEED_PAGE_SIZE).cachedIn(viewModelScope)
    val updatedList = mutableStateListOf<NewsModel>()

    private var _uiState: FeedUiState by mutableStateOf(FeedUiState.Default)
    val uiState: StateFlow<FeedUiState> = snapshotFlow { _uiState }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(),
        initialValue = _uiState
    )

    fun onScoreButtonClicked(model: NewsModel, value: Boolean) {
        viewModelScope.launch {
            runCatching {
                when (_uiState) {
                    FeedUiState.Default -> feedRepository.setNewsScore(model.id, value)
                    FeedUiState.Recommendation -> recommendationFeedRepository.setNewsScore(
                        model.id,
                        value
                    )
                }
            }.onSuccess {
                    updatedList.add(
                        model.copy(
                            trustIndex = it,
                            currentUserScore = value
                        )
                    )
                }
        }
    }

    fun onStateChanged(newState: FeedUiState) {
        _uiState = newState
    }

    sealed interface FeedUiState {
        val name: String

        object Default : FeedUiState {
            override val name = "Актуальные новости"
        }

        object Recommendation : FeedUiState {
            override val name = "Рекомендованные новости"
        }
    }

    companion object {
        private const val FEED_PAGE_SIZE = 4
    }
}