package com.ewake.truepaper.features.feed.domain.repository

import androidx.paging.PagingData
import com.ewake.truepaper.core.models.domain.NewsModel
import com.ewake.truepaper.core.models.domain.TrustIndexModel
import kotlinx.coroutines.flow.Flow

/**
 * @author Nikolaevskiy Dmitriy
 */
interface FeedRepository {
    fun getPagingFeedFlow(count: Int): Flow<PagingData<NewsModel>>
    suspend fun setNewsScore(newsId: Int, score: Boolean): TrustIndexModel
}