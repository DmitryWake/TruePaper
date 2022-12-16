package com.ewake.truepaper.features.feed.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.ewake.truepaper.features.feed.domain.repository.FeedRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

/**
 * @author Nikolaevskiy Dmitriy
 */
@HiltViewModel
class FeedViewModel @Inject constructor(
    private val feedRepository: FeedRepository
) : ViewModel() {

    val feedFlow = feedRepository.getPagingFeedFlow(FEED_PAGE_SIZE).cachedIn(viewModelScope)

    companion object {
        private const val FEED_PAGE_SIZE = 4
    }
}