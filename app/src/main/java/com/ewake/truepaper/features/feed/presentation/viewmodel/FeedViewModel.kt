package com.ewake.truepaper.features.feed.presentation.viewmodel

import androidx.lifecycle.ViewModel
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

    val feedFlow = feedRepository.getPagingFeedFlow(10)
}