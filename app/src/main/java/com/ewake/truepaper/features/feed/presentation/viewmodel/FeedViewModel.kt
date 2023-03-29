package com.ewake.truepaper.features.feed.presentation.viewmodel

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.ewake.truepaper.core.models.domain.NewsModel
import com.ewake.truepaper.features.feed.domain.repository.FeedRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * @author Nikolaevskiy Dmitriy
 */
@HiltViewModel
class FeedViewModel @Inject constructor(
    private val feedRepository: FeedRepository
) : ViewModel() {

    val feedFlow = feedRepository.getPagingFeedFlow(FEED_PAGE_SIZE).cachedIn(viewModelScope)
    val updatedList = mutableStateListOf<NewsModel>()

    fun onScoreButtonClicked(model: NewsModel, value: Boolean) {
        viewModelScope.launch {
            runCatching { feedRepository.setNewsScore(model.id, value) }
                .onSuccess {
                    updatedList.add(
                        model.copy(
                            trustIndex = it,
                            currentUserScore = value
                        )
                    )
                }
        }
    }

    companion object {
        private const val FEED_PAGE_SIZE = 4
    }
}