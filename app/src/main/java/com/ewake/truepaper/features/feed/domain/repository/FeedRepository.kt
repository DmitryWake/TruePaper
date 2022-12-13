package com.ewake.truepaper.features.feed.domain.repository

import androidx.paging.PagingData
import com.ewake.truepaper.core.models.domain.NewsModel
import kotlinx.coroutines.flow.Flow

/**
 * @author Nikolaevskiy Dmitriy
 */
interface FeedRepository {
    fun getPagingFeedFlow(pageSize: Int): Flow<PagingData<NewsModel>>
}